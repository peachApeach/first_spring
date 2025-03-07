package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP 사용 시 @Aspect Annotaion 필요
@Aspect

public class TimeTraceAop {
    // 타겟팅 -> hello.hello_spring의 모든 하위 목록에 적용
    @Around("execution(* hello.hello_spring..*(..)) && !target(hello.hello_spring.SpringConfig)\"")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // currentTimeMillis : 현재 시스템 시간 ms 변환
        long start = System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString());

        try {
            // joinPoint.proceed : 실행 시 다음 process를 가져오는 메서드
            Object result = joinPoint.proceed();
            return  result;
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+joinPoint.toString() + " " + timeMs+"ms");
        }

    }
}
