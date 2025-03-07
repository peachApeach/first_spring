package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//
//    /*// 데이터베이스를 스프링으로부터 받아오기 위한 설정
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }*/
//
//    //멤버 서비스와 멤버 리포지토리를 스프링빈에 등록하고 스프링빈에 등록되어 있는 멤버 리포지토리를 멤버 서비스에 넣어준다.
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
////    @Bean
////    public MemberRepository memoryMemberRepository() {
////        return new MemoryMemberRepository();
////    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 의존성 설정
    @Bean
    public MemberService memberService() {
        return  new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
