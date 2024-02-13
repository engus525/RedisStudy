package self.redisstudy.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.redisstudy.domain.Member;
import self.redisstudy.repository.MemberRedisRepository;
import self.redisstudy.repository.MemberRepository;
import self.redisstudy.web.dto.request.MemberRequestDto;
import self.redisstudy.web.dto.request.MemberRequestDto.MemberSignUpDto;
import self.redisstudy.web.dto.response.MemberResponseDto;
import self.redisstudy.web.dto.response.MemberResponseDto.MemberShowDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRedisRepository memberRedisRepository;
    private final MemberRepository memberRepository;

    @Override
    public Member save(MemberSignUpDto request) {

        Member newMember = MemberRequestDto.toEntity(request);
        Member savedMember = memberRepository.save(newMember);

        //Save in redis
        MemberShowDto memberShowDto = MemberResponseDto.from(savedMember);
        memberRedisRepository.save(memberShowDto);

        return savedMember;
    }

    @Override
    public MemberResponseDto.MemberShowDto findMember(Long memberId) {

        //Cache Logic
        Optional<MemberResponseDto.MemberShowDto> optionalMemberDto = memberRedisRepository.findById(memberId);
        if (optionalMemberDto.isPresent()) {
            log.info("[Member Dto] Cache Exist!");
            return optionalMemberDto.get();
        } else {
            log.info("[Member Dto] Cache Not Exist.");
        }

        log.info("DB에서 조회");
        Member member = memberRepository.findById(memberId).get();
        MemberShowDto memberShowDto = MemberResponseDto.from(member);

        log.info("다시 Redis에 캐싱");
        memberRedisRepository.save(memberShowDto);

        log.info("[Member Dto] : {}", memberShowDto);
        return memberShowDto;

    }
}
