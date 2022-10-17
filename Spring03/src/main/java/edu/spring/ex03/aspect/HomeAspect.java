package edu.spring.ex03.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Proxy 객체에 주입하기위해 선언
@Aspect // Aspect = Advice + Pointcut
public class HomeAspect {
	private static final Logger logger = LoggerFactory.getLogger(HomeAspect.class);
	
	// Aspect
	// 일반적으로 메소드에 특정 기능을 적용시킴
	// 메소드 실행 전후에 특정 기능을 적용시킬 수 있음
	// Filter, Interceptor, AOP 세 가지 기능은 특정 행동 실행 전 후 행동을 제어 할 때 사용
	// 요청이 들어오면 Filter → Interceptor → AOP → Interceptor → Filter 순으로 거침
	// Filter 보다 Aspect가 더 섬세하게 컨트롤 가능
	
	// Pointcut을 지정하는 방법
	// 1. @Before, @After, .. 어노테이션 안에서 지정
	// 2. @PointCunt 어노테이션 안에서 지정
	// 동일한 PointCunt이 반복되는것을 피할 수 있음
	// 한번 지정한 PointCunt을 여러 advice메소드에서 참조
	
	
}
