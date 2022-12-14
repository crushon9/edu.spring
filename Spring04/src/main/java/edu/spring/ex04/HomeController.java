package edu.spring.ex04;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		logger.info(formattedDate);
		model.addAttribute("serverTime", formattedDate);
		return "home"; // home.jsp
	}

	@GetMapping("/test1") // home 메소드 밖에 있으니깐 ex04/test1 이 됨
	public String test1() {
		logger.info("test1() 호출");
		return "test"; // test.jsp
	}

	@GetMapping("/test2")
	public String test2(Model model) {
		logger.info("test2() 호출");
		model.addAttribute("data", "test2");
		return "test";
	}

}
