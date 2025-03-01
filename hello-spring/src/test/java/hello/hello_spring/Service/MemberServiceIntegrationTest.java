package hello.hello_spring.Service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 일회성 테스트이기 때문에 필드 기반 autowired 사용 시 편리
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    // repository에 있던 AfterEach 함수를 가져와 각 케이스별 작업이 완료되면 객체를 비우게 된다.
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("NAME");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 중복 회원 점검
    @Test
    public void validateUser() {
        // given
        Member member1 = new Member();
        member1.setName("NAME");

        Member member2 = new Member();
        member2.setName("NAME");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}