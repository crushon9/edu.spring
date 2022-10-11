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
		return "sample1"; // jsp 호출
	}

	@GetMapping("/json2")
	@ResponseBody // jsp를 안보내고 다른걸 보낼때..
	public String json2() {
		logger.info("json2() 호출");
		return "Hello, Spring"; // 데이터 자체를 리턴
	}
	
	@GetMapping("/json3")
	@ResponseBody
	public ProductVO json3() {
		logger.info("json3() 호출");
		return new ProductVO("야구공", 10000);
	}
}
