package edu.spring.ex04.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public void loginGET() {
		logger.info("loginGET() 호출");
	}

	@PostMapping("/login")
	public String loginPOST(String memberId, String password, HttpServletRequest request) {
		logger.info("loginPOST() 호출");
		if (memberId.equals("test") && password.equals("1234")) {
			logger.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);

			// 세션에서 targetURL 가져오기
			String targetURL = (String) session.getAttribute("targetURL");
			logger.info("targetURL : " + targetURL);
			if (targetURL != null) { // targetURL 있으면
				session.removeAttribute("targetURL"); // 세션을 삭제하고
				return "redirect:" + targetURL;
			} else { // targetURL 없으면
				return "redirect:/board/list";
			}
		} else {
			logger.info("로그인 실패");
			return "redirect:/member/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout() 호출");
		HttpSession session = request.getSession();
		session.removeAttribute("memberId");
		return "redirect:/board/list";
	}
}
