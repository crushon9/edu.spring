package edu.spring.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.spring.ex01.domain.ProductVO;

@Controller
public class SampleJSONController {

	private static final Logger logger = LoggerFactory.getLogger(SampleJSONController.class);

	@GetMapping("/json1")
	public String json1() {
		logger.info("json1() 호출");
		return "sample1"; // WEB-INF/views/sample1.jsp 리턴
	}

	@GetMapping("/json2")
	// @ResponseBody : View 페이지가 아닌 반환값 그대로 클라이언트한테 return
	@ResponseBody
	public String json2() {
		logger.info("json2() 호출");
		return "Hello, Spring"; // 데이터 자체를 리턴
	}
	
	@GetMapping("/json3")
	@ResponseBody
	public ProductVO json3() {
		logger.info("json3() 호출");
		return new ProductVO("야구공", 10000); // 객체를 리턴, 출력결과{"name":"야구공","price":10000}
	}
}
