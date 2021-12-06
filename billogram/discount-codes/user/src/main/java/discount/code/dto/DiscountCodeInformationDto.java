package discount.code.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountCodeInformationDto {

    @NotNull(message = "brandId cannot be null")
    private int brandId;

    @NotNull(message = "UserId cannot be null")
    private int userId;

}
