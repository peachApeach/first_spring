package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository<class, 식별자 타입> 를 사용하면 구현체를 만들어 spring bean을 자동으로 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL = select m from Member m where m.name = ?
    Optional<Member> findByName(String name);
}
