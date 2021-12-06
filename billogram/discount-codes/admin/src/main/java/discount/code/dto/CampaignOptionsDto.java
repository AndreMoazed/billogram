package discount.code.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.DiscountTypeEnum;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignOptionsDto {

    @NotNull(message = "brandId cannot be null")
    private int brandId;

    private String externalDiscountCode;

    @NotNull(message = "A Discount type mus be selected it cannot be null")
    private DiscountTypeEnum discountType;

    private int discountTypeValue;

    private Date startDate;

    // In a full implementation will have brand configurations that will apply a default Campaign length if the
    // expiry date is not provided
    private Date endDate;

    private int numberOfCodes;

    private boolean unlimited;

    String campaignImage;
    String campaignName;

}
