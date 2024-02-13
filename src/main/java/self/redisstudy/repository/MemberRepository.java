package self.redisstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import self.redisstudy.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
