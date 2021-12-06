package discount.code.generation.services;

import dao.DiscountCodeRepository;
import discount.code.dto.CampaignOptionsDto;
import discount.code.dto.GenerateNewCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.Instant;

// Service class to abstract the logic away from the REST access.
// This class would have more logic to deal with more complex data validation, i.e.
public class GenerateNewCodesService {

    private final DiscountCodeRepository discountCodeRepository;

    @Autowired
    GenerateNewCodesService(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    public GenerateNewCodeResponse generateNewCodes(CampaignOptionsDto campaignOptions) {
        if (null == campaignOptions) {
            return GenerateNewCodeResponse.builder()
                    .codeGenerationSuccess(false)
                    .message("Missing values in the campaign options")
                    .build();
        }
        Date startDate = (null == campaignOptions.getStartDate() ? (Date) Date.from(Instant.now()) : (Date) campaignOptions.getStartDate());
        Date expiryDate = (null == campaignOptions.getEndDate() ? (Date) Date.from(Instant.now()) : (Date) campaignOptions.getEndDate());

        // Perform the update and check that the effected rows are > 0, that way
        if(discountCodeRepository.addNewDiscountCodes(campaignOptions.getBrandId(), campaignOptions.getExternalDiscountCode(),
                campaignOptions.getDiscountType().toString(), campaignOptions.getDiscountTypeValue(), startDate, expiryDate,
                campaignOptions.isUnlimited(), campaignOptions.getNumberOfCodes(), campaignOptions.getCampaignName(), campaignOptions.getCampaignImage()) > 0) {

            return GenerateNewCodeResponse.builder()
                    .codeGenerationSuccess(true)
                    .message("Campaign successfully created!")
                    .build();
        }

        // Message would be more intricate with a proper error handling solution. An error handler, helper class etc.
        // would ensure that correct and appropriate messages get returned to display more suitable error codes in a
        // full implementation of this program
        return GenerateNewCodeResponse.builder()
                .codeGenerationSuccess(false)
                .message("Unable to generate discount codes with the provided options")
                .build();
    }
}
