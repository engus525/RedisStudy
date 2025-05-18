package kdh.redisstudy.domain.string.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kdh.redisstudy.domain.string.model.request.MultiStringRequest;
import kdh.redisstudy.domain.string.model.request.StringRequest;
import kdh.redisstudy.domain.string.model.response.StringResponse;
import kdh.redisstudy.service.StringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "StringController", description = "string api")
@RestController
@RequestMapping("/api/v1/set")
@RequiredArgsConstructor
public class StringController {

    private final StringService stringService;

    @PostMapping("/set-string-collection")
    public void setString(
        @RequestBody @Valid StringRequest request
    ) {
        stringService.setString(request);
    }

    @GetMapping("/get-string-collection")
    public StringResponse getString(
        @RequestParam @Valid String key
    ) {
        return stringService.getString(key);
    }

    @PostMapping("/multi-set-collection")
    public void multiString(
        @RequestBody @Valid MultiStringRequest request
    ) {
        stringService.multiString(request);
    }
}
