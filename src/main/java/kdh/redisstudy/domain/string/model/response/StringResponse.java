package kdh.redisstudy.domain.string.model.response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import kdh.redisstudy.domain.string.model.StringModel;

@Schema(description = "redis string response")
public record StringResponse(

    @Schema(description = "set string response")
    List<StringModel> response
) {

}
