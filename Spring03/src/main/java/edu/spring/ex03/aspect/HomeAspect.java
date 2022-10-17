package edu.spring.ex03.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Proxy 객체에 주입하기위해 선언
@Aspect // Aspect = Advice + Pointcut
public class HomeAspect {
	private static final Logger logger = LoggerFactory.getLogger(HomeAspect.class);

	// @Aspect
	// 일반적으로 메소드에 특정 기능을 적용시킴
	// 메소드 실행 전후에 특정 기능을 적용시킬 수 있음
	// Filter, Interceptor, AOP 세 가지 기능은 특정 행동 실행 전 후 행동을 제어 할 때 사용
	// 요청이 들어오면 Filter → Interceptor → AOP → Interceptor → Filter 순으로 거침
	// Filter 보다 Aspect가 더 섬세하게 컨트롤 가능

	// Before	메소드 실행 전에 동작
	// After	메소드 실행 후에 동작
	// After-returning	메소드가 정상적으로 실행된 후에 동작
	// After-throwing	예외가 발생한 후에 동작
	// Around	메소드 호출 이전, 이후, 예외발생 등 모든 시점에서 동작
	
	// @Pointcut을 지정하는 방법
	// 1. @Before, @After, .. 어노테이션 안에서 지정
	// 2. @Pointcut 어노테이션 안에서 지정
	// 동일한 @Pointcut이 반복되는것을 피할 수 있음
	// 한번 지정한 @Pointcut을 여러 advice메소드에서 참조

	@Pointcut("execution(* edu.spring.ex03.HomeController.home(..))") // home(..) : 메소드
	public void pcHome() { // 포인트컷 위치 지정
	}

	@Around("pcHome()") // 포인트컷 메소드를 적용
	public Object homeAdvice(ProceedingJoinPoint jp) {
		Object result = null;
		logger.info("======= homeAdvice() 호출");

		try {
			logger.info("======= home() 호출 전"); // @Before

			result = jp.proceed(); // HomeController.home() 실행

			logger.info("======= home() 리턴 후"); // @AfterReturning

		} catch (Throwable e) {
			// @AfterThrowing
			logger.info("======= 예외 발생 : " + e.getMessage());
		} finally {
			logger.info("======= finally");
		}

		return result;
	}

}
