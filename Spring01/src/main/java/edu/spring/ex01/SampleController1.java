package edu.spring.ex01;

import org.slf4j.Logger; // Logger import 패키지가 많으니 주의
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*POJO : Plain Old Java Object
자바 표준 문서 JLS(Java Language Specification)에 의해 강제된 것 이외의 제한에 의존하지 않는 자바 오브젝트
상속 받지 않고, 다른 환경의 호환에 영향받지 않는 단일 책임 원칙(SRP)을 지키는 클래스 객체*/

@Controller
/* @Controller 어노테이션을 쓰면 서블릿 상속받은것처럼 클래스에 적용됨 
 * servlet-context.xml 파일 혹은 root-context.xml에서 component-scanning의 대상으로 만들어주는 어노테이션
 * @Component 어노테이션을 사용해도 됨 */
public class SampleController1 {
	// 스프링 프레임워크에서 로그를 사용하기 위한 객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController1.class);

	/* @RequestMapping : 컨트롤러의 URL 매핑과 메소드(GET/POST/PUT/DELETE)를 설정 
	 * value = URL 경로
	 * method = 요청 방식 설정(GET/POST/PUT/DELETE), 속성 생략하면 GET/POST/PUT/DELETE 모두 처리 가능
	 * ShortCut : @GetMapping, @PostMapping, @PutMapping, @DeleteMapping */
	@RequestMapping(value = "/sampleReturn", method = RequestMethod.GET)
	public String sampleReturn() {
		logger.info("sampleReturn() 호출");
		return "sample1"; // jsp 경로 및 이름
		// WEB-INF/views/sample1.jsp 로 찾아감 (servlet-context.xml에 prefix접두 suffix접미 설정되어 있음)
		
		/* 컨트롤러 메소드 return 값의 의미 
		 * ViewResolver에서 실제 view를 결정하도록(찾도록) 요청 
		 * return 타입이 void인 경우는 URL 매핑을 통해서 view를 찾음 */
	} // end sample1()
	
	@RequestMapping(value = "/sample2")
	public void sample2() {
		logger.info("sample2() 호출");
		// 리턴 문자열이 없으면 url value 이름에 맞는 jsp를 찾아서 적용 즉 WEB-INF/views/sample1.jsp 반환
	} // end sample2()

} // end SampleController1
