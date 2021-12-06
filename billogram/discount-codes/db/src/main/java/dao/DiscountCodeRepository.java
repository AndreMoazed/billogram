package dao;

import entity.DiscountCodeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

// This interface is used for modifying and retrieving data to do with discount codes
public interface DiscountCodeRepository extends CrudRepository<DiscountCodeEntity, Integer> {

    // Will look for all discount codes associated with a specific User and brand. Used to display the available
    // discount codes for Users in the brand cart checkout page
    @Query(value = "SELECT dc.* " +
            " FROM discount_codes dc " +
                " JOIN user_discount_codes udc on dc.discount_code_id = udc.discount_code_id " +
                " JOIN users u on u.user_id = udc.user_id " +
            " WHERE u.user_id = :userId " +
                " AND dc.brand_id = :brandId " +
                " AND dc.discount_code_expiry < CURRENT_DATE " +
                " AND udc.claimed = false", nativeQuery = true)
    List<DiscountCodeEntity> findUserDiscountCodesByUserIdAndBrandId(@Param("brandId") int brandId,
                                                                     @Param("userId") int userId);

    // Will search for all discount codes associated with a User. This will be displayed when the User chooses
    // to view their full list of discount codes
    @Query(value = "SELECT dc.* " +
            " FROM discount_codes dc " +
            " JOIN user_discount_codes udc on dc.discount_code_id = udc.discount_code_id " +
            " JOIN users u on u.user_id = udc.user_id " +
            " WHERE u.user_id = :userId " +
            " AND dc.discount_code_expiry < CURRENT_DATE " +
            " AND udc.claimed = false", nativeQuery = true)
    List<DiscountCodeEntity> findAllUserDiscountCodesByUserId(@Param("brandId") int brandId,
                                                              @Param("userId") int userId);

    // A stored procedure will also be added for when a discount code is claimed that will:
    //      - insert a new row into the user_discount_codes table with the code and user_id
    //      - if the number of codes is limited it will decrement the available codes in the discount_codes table
    @Modifying
    @Query(value = "INSERT INTO user_discount_codes (user_id, discount_code_id, discount_code_external_id, claimed)" +
            " VALUES (:userId, :discountCodeId, :externalDiscountCode, false);", nativeQuery = true)
    int claimDiscountCode(String externalDiscountCode, int userId, int discountCodeId);

    // Will update the used discount code column so that the code can't be used again, we will also use this to see if
    // a user has claimed a discount code of this type already and so will not be allowed to claim another of the same
    // type.
    @Modifying
    @Query(value = "UPDATE user_discount_codes udc " +
            " SET claimed = true " +
            " WHERE udc.user_id = :userId" +
            " AND udc.external_discount_code_id = :externalDiscountCode", nativeQuery = true)
    int useDiscountCode(String externalDiscountCode, int userId);

    @Modifying
    @Query(value =  " INSERT INTO discount_codes (brand_id, discount_code_external_id, discount_type, discount_type_value, start_date, expiry_date, unlimited, remaining_codes, campaign_name, campaign_image) " +
                    " VALUES (:brandId, :discountCodeExternalId, :discountType, :value, :startDate, :endDate, :isUnlimited, :numberOfCodes, :campaignName, :campaignImage)",
            nativeQuery = true)
    int addNewDiscountCodes(@Param("brand_id") int brandId,
                            @Param("discount_code_external_id") String discountCodeExternalId,
                            @Param("discount_type") String discountType,
                            @Param("discount_type_value") int value,
                            @Param("start_date") Date startDate,
                            @Param("end_date") Date endDate,
                            @Param("unlimited") boolean isUnlimited,
                            @Param("remaining_codes") int numberOfCodes,
                            @Param("campaign_name") String campaignName,
                            @Param("campaign_image") String campaignImage);
}
