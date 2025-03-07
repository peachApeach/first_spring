package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// 테스트이기 때문에 public일 필요 없음
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // method가 끝날 때마다 호출되는 콜백 메소드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // 작성했던 save 함수 실행
    @Test
    public void save(){
        Member member = new Member();
        member.setName("NAME");
        
        // 생성한 객체 리포지토리 저장 후 id 확인
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // memeber와 result가 같으면 정상적으로 작동한 것
        // Assertions.assertEquals(object, object)를 이용
        Assertions.assertEquals(member, result);
        //assertj에서 제공하는 Assertions 사용법
        //Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
