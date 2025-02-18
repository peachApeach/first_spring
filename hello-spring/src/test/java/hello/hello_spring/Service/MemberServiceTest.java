package hello.hello_spring.Service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    // clear 작업을 위한 레포지토리 사용
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

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
        //1)
        //Long saveId = memberService.join(member);

        //3)
        // 현재 member1과 2는 같은 이름으로 되어 있기 때문에 중복 오류가 발생한다.
        // 중복 오류가 발생할 경우 IllegalStateException 오류가 발생된다.
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /*2)
        //try catch로 중복 회원 점검이 가능하다
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            //오류 성공
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        // then
        //3)
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //1)
        //Member findMember = memberService.findOne(saveId).get();
        //Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}