package edu.spring.ex01.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SampleVOController {
	private static final Logger logger = LoggerFactory.getLogger(SampleVOController.class);

	@GetMapping("/product1")
	public String product1(Model model, String name, int price) {
		// URL : /product1?name=문자열입력값&price=숫자입력값
		logger.info("product1() 호출");
		ProductVO vo = new ProductVO(name, price);
		model.addAttribute("vo", vo);
		return "product-result"; // WEB-INF/views/product-result.jsp 페이지 반환
	}

	@GetMapping("/product2")
	public String product2(@ModelAttribute(name = "vo") ProductVO vo) {
		// URL : /product2 입력시 기본생성자를 view로 전달
		logger.info("product2() 호출");
		return "product-result";
	}
}
