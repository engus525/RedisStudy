package kdh.redisstudy.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "base key request")
public record BaseRequest (

    @Schema(description = "redis key")
    @NotBlank
    String Key
) {

}
