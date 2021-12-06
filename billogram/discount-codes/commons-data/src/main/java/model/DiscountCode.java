package model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DiscountCode {
    int discountCodeId;
    int brandId;
    String discountType;
    int discountTypeValue;
    String campaignName;
    String campaignImage;
    String externalDiscountCode;
    Date expiryDate;
    Date startDate;
    int remainingCodes;
}
