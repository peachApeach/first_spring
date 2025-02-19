package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    //// 회원 서비스를 만들려면  repository가 필요
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 같은 객체를 사용하기 위한 방법
    private final MemoryMemberRepository memberRepository;

    //@Autowired
    public MemberService(MemoryMemberRepository memberRepository) {
        // 변수로 선언하여 repository를 외부에서 삽입하도록 변경
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    // 중복 이름이 있는 회원 가입 거절
    public Long join(Member member) {
        // = 다음 부분 입력 후 ctrl + alt + v (Mac : command + option + v)
        // optional로 반환되도록 전환 
        Optional<Member> result = memberRepository.findByNames(member.getName());
        validateName(result);       // 중복회원 검증

        memberRepository.save(member);
        return member.getId();      // 생성한 member의 id 조회
    }

    private static void validateName(Optional<Member> result) {
        // result에 값이 있으면 아래 동작을 실행하고 멤버를 추가하지 않는다.
        // Optional을 사용했기 때문에 if문을 사용하지 않아도 비교구문 사용 가능
        // IllegalStateException : 개발자의 코드가 사용자의 값이 수용하지 못하는 상태일 경우 발생하는 오류
        // 같은 값이 있을 경우 오류 메시지 송출
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    // id로 회원 찾기
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
