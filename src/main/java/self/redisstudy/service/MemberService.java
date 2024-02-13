package self.redisstudy.service;

import self.redisstudy.domain.Member;
import self.redisstudy.web.dto.request.MemberRequestDto.MemberSignUpDto;
import self.redisstudy.web.dto.response.MemberResponseDto;

public interface MemberService {

    Member save(MemberSignUpDto request);
    MemberResponseDto.MemberShowDto findMember(Long memberId);
}
