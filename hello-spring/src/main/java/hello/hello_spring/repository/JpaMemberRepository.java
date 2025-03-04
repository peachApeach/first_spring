package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // gradle이 받은 jpa 라이브러리에서 생성한 EntityManager를 사용하여 데이터베이스 사용
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // 저장
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // pk일 경우 아래와 같이 사용 가능 : 유형, 식별자
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByNames(String name) {
        // 객체를 대상으로 쿼리를 사용
        List<Member> result = em.createQuery("select m from Member m where name=:name",Member.class).setParameter("name",name).getResultList();
        // 결과 하나 반환
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 객체를 대상으로 쿼리를 사용
        // *이 아닌 객체 선택
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
