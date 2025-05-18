package kdh.redisstudy.domain.string.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import kdh.redisstudy.common.request.BaseRequest;

@Schema(description = "redis string collection request")
public record StringRequest(
    BaseRequest baseRequest,

    @Schema(description = "name")
    @NotBlank
    String Name
) {

}
