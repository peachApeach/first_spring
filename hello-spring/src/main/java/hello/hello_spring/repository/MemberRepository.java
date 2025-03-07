package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // Member of Domin
    Member save(Member member);
    // optional은 java 8에  들어간 기능인데 null이 반환될 경우 처리할 수 있는 기능
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
