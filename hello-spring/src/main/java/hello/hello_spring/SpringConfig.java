//package hello.hello_spring;
//
//import hello.hello_spring.repository.MemoryMemberRepository;
//import hello.hello_spring.service.MemberService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringConfig {
//
//    //멤버 서비스와 멤버 리포지토리를 스프링빈에 등록하고 스프링빈에 등록되어 있는 멤버 리포지토리를 멤버 서비스에 넣어준다.
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memoryMemberRepository());
//    }
//
//    @Bean
//    public MemoryMemberRepository memoryMemberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//}
