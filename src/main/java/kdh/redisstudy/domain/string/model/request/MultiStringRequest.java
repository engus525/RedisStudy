package kdh.redisstudy.domain.string.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import kdh.redisstudy.common.request.BaseRequest;

@Schema(description = "string multiset request")
public record MultiStringRequest(
    BaseRequest baseRequest,

    @Schema(description = "names")
    @NotBlank
    String[] Names
) {

}
