package self.redisstudy.web.dto.response;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import self.redisstudy.domain.Member;

public record MemberResponseDto() {

    @Builder
    @RedisHash(value = "member", timeToLive = 20)
    public record MemberShowDto (@Id Long redisKey, String name) {}

    public static MemberShowDto from(Member member) {
        return MemberShowDto
            .builder()
            .redisKey(member.getId())
            .name(member.getName())
            .build();
    }
}
