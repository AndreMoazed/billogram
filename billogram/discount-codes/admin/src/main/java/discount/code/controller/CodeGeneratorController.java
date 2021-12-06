package discount.code.controller;

import discount.code.dto.CampaignOptionsDto;
import discount.code.dto.GenerateNewCodeResponse;
import discount.code.generation.services.GenerateNewCodesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

// Controller class that sets up the endpoint for requests.
// This class will not have business logic, preliminary data validation will be done on the front end
// i.e. disallowing user to input external discount code ids that already exist. This information is already on the page
// as all ongoing campaigns will be fetched on loading the page.
@Slf4j
@RestController
@RequestMapping(value = AdminApi.rootPath, produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeGeneratorController {

    private final GenerateNewCodesService generateNewCodesService;

    @Autowired
    public CodeGeneratorController(GenerateNewCodesService generateNewCodesService) {
        this.generateNewCodesService = generateNewCodesService;
    }

    @RequestMapping(value = AdminApi.GENERATE_NEW_CODE, method = RequestMethod.POST)
    GenerateNewCodeResponse generateDiscountCodes(@RequestBody CampaignOptionsDto campaignOptions, HttpServletResponse response) {
        // Admin will have choice of if they want to submit their own or have an autogenerated code
        // For this a code generator service will be needed to make more suitable, brand specific, external code id's
        // For example will take brand name + year + random 6 letters string
        // Validation will be added to check that the external ID does not already exist.

        // Would call on the random discount code string service...
        if (null == campaignOptions.getExternalDiscountCode()) {
            campaignOptions.setExternalDiscountCode("random-string");
        }

        GenerateNewCodeResponse codeResponse = generateNewCodesService.generateNewCodes(campaignOptions);
        if (codeResponse.isCodeGenerationSuccess()) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Error handler would be used to handle the more intricate and correct responses depending on what error
            // occurs or what went wrong with the request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return codeResponse;
    }
}
