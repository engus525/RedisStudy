package self.redisstudy.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.redisstudy.domain.Member;
import self.redisstudy.service.MemberService;
import self.redisstudy.web.dto.request.MemberRequestDto;
import self.redisstudy.web.dto.response.MemberResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto.MemberShowDto> getMember(@PathVariable Long memberId) {
        long startTime = System.currentTimeMillis();
        MemberResponseDto.MemberShowDto memberDto = memberService.findMember(memberId);
        long endTime = System.currentTimeMillis();

        log.info("[GET /{memberId}] Response Time : {} ms", (endTime - startTime));
        return ResponseEntity.ok(memberDto);
    }

    @PostMapping()
    public ResponseEntity<String> saveMember(MemberRequestDto.MemberSignUpDto request) {
        Member member = memberService.save(request);
        return ResponseEntity.ok("생성된 Member Dto의 Key = " + member.getId());
    }

}
