package self.redisstudy.repository;

import org.springframework.data.repository.CrudRepository;
import self.redisstudy.web.dto.response.MemberResponseDto;

public interface MemberRedisRepository extends CrudRepository<MemberResponseDto.MemberShowDto, Long> {

}
