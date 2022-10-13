package edu.spring.ex02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Component 모든 Spring 관리 구성 요소에 대한 일반 스테레오타입
//ㄴ @Repository *영속 계층(Persistence Layer)
//ㄴ @Service *서비스 계층 (Service/Business Layer)
//ㄴ @Controller *표현 계층(Presentation Layer)

@Controller // @Component 의 하위 어노테이션
//* 표현 계층(Presentation Layer)
// View(페이지)와 Service를 연결하는 역할
// request에 대한 response를 전달하는 역할
@RequestMapping(value = "/board") // url : /ex02/board
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@GetMapping("/list")
	public void list(Integer page, Integer numsPerPage) {
		logger.info("list(); 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
	}

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET(); 호출");
	}

}
