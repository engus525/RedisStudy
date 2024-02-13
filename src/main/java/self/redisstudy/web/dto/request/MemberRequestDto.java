package self.redisstudy.web.dto.request;


import self.redisstudy.domain.Member;

public record MemberRequestDto() {

    public record MemberSignUpDto(String name) {}
    public static Member toEntity(MemberSignUpDto request) {
        return Member
            .builder()
            .name(request.name)
            .build();
    }
}