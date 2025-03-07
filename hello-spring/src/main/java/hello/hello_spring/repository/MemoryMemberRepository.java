package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 이와 같이 공유되는 변수일 경우 HashMap을 사용하면 동시성 문제가 발생할 수 있으나
    // 간단한 예제이므로 그대로 사용
    // 실무에서는 concurrenthashmap, AtomicLong 사용
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // id는 시스템에서 지정하는 값이며 name은 사용자가 입력한 값으로 가정
        // store Map에 저장
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 id로 조회
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // findAny는 하나라도 찾는 것을 의미
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
