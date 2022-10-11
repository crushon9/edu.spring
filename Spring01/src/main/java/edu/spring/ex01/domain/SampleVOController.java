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
		logger.info("product1() 호출");
		ProductVO vo = new ProductVO(name, price);
		model.addAttribute("vo", vo);
		return "product-result";
	}

	@GetMapping("/product2")
	public String product2(@ModelAttribute(name = "vo") ProductVO vo) {
		logger.info("product2() 호출");
		return "product-result";
	}
}
