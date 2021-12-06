package discount.code.services;

import dao.DiscountCodeRepository;
import discount.code.dto.DiscountCodeInformationDto;
import discount.code.dto.RetrieveCodeResponse;
import entity.DiscountCodeEntity;
import model.DiscountCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveCodeService {

    private final DiscountCodeRepository discountCodeRepository;

    @Autowired
    RetrieveCodeService(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

     public RetrieveCodeResponse retrieveUserBrandCodes(DiscountCodeInformationDto discountCodeInformation) {
         List<DiscountCodeEntity> entity = discountCodeRepository.
                 findUserDiscountCodesByUserIdAndBrandId(discountCodeInformation.getBrandId(),
                         discountCodeInformation.getUserId());

         if (entity == null || entity.isEmpty()) {
             return null;
         } else {
             return RetrieveCodeResponse.builder()
                     .discountCodes(discountCodeRepository.
                             findUserDiscountCodesByUserIdAndBrandId(discountCodeInformation.getBrandId(),
                                     discountCodeInformation.getUserId()).stream().map(this::buildDiscountCode)
                             .collect(Collectors.toList()))
                     .build();
         }
     }

     private DiscountCode buildDiscountCode(DiscountCodeEntity entity) {

        return DiscountCode.builder()
                .discountCodeId(entity.getDiscountCodeID())
                .discountType(entity.getDiscountType().getType().toString())
                .discountTypeValue(entity.getDiscountType().getValue())
                .externalDiscountCode(entity.getDiscountCodeExternalId())
                .brandId(entity.getBrandId())
                .startDate(entity.getCodeStartDate())
                .expiryDate(entity.getCodeExpiryDate())
                .campaignName(entity.getCampaignName())
                .campaignImage(entity.getCampaignImage())
                .build();
     }
}
