package discount.code.controller;

import dao.DiscountCodeRepository;
import discount.code.dto.DiscountCodeInformationDto;
import discount.code.dto.RetrieveCodeResponse;
import discount.code.services.RetrieveCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = UserApi.rootPath, produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeRetrievalController {

    private final RetrieveCodeService retrieveCodeService;

    @Autowired
    CodeRetrievalController(RetrieveCodeService retrieveCodeService) {
        this.retrieveCodeService = retrieveCodeService;
    }

    @RequestMapping(value = UserApi.RETRIEVE_CODES, method = RequestMethod.POST)
    RetrieveCodeResponse generateNewCodes(@RequestBody DiscountCodeInformationDto discountCodeInformation, HttpServletResponse response) {
        if (null == discountCodeInformation) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        RetrieveCodeResponse codeResponse = retrieveCodeService.retrieveUserBrandCodes(discountCodeInformation);
        if (null != codeResponse ) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Error handler would be used to handle the more intricate and correct responses depending on what error
            // occurs or what went wrong with the request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return null;
    }
}
