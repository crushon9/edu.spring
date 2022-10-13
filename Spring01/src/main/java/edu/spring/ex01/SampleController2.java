package edu.spring.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController2 {
	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);

	@GetMapping("/test1") // Short Cut
	public String test1(Model model, String username) {
		// username : get방식 request에 담긴 파라미터의 이름 /test1?username="입력값"
		// username == request.getParameter("username");
		logger.info("test1() 호출 : username = " + username);

		// Model : view에 데이터를 전송하기 위한 객체
		// 서블릿의 response 대신에 model을 쓰네?
		model.addAttribute("username", username);

		return "param-test"; // WEB-INF/views/param-test.jsp 페이지 반환
	}

	@GetMapping("/test2")
	public String test2(Model model, String username, int age) {
		logger.info("test2() 호출 ");
		logger.info("username = " + username);
		logger.info("age = " + age);

		model.addAttribute("username", username);
		model.addAttribute("age", age);

		return "param-test";
	}
}
