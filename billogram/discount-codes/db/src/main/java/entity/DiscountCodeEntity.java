package entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
@Entity(name = "discount_code")
@Table(name = "discount_codes", schema = "public")
public class DiscountCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_code_id")
    int discountCodeID;

    @Column(name = "brand_id")
    int brandId;

    @Column(name = "discount_code_external_id")
    String discountCodeExternalId;

    @Column(name = "campaign_name")
    String campaignName;

    @Column(name = "campaign_image")
    String campaignImage;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "type", column = @Column(name = "discount_type")),
            @AttributeOverride(name = "typeValue", column = @Column(name = "discount_type_value"))
    })
    @Nullable
    DiscountTypeEntity discountType;

    @Column(name = "start_date")
    Date codeStartDate;
    @Column(name = "expiry_date")
    Date codeExpiryDate;

    @Column(name = "remaining_codes")
    int remainingCodes;

    @Column(name = "unlimited")
    boolean unlimited;
}
