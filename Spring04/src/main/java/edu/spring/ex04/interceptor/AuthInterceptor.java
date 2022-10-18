package edu.spring.ex04.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("==== preHandle()호출");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		if (memberId != null) {
			// 로그인 상태(세션 존재) : mapping된 URL의 컨트롤러 메소드 실행
			logger.info("로그인 상태 -> controller method 실행");
			return true;
		} else {
			// 로그아웃 상태(세션 없음) : login URL로 이동, target URL 저장
			logger.info("로그아웃 상태 -> controller method 실행 안됨");
			// target URL을 session에 저장 (만약 request parameter가 존재하면 같이 저장)
			saveDestination(request);
			response.sendRedirect("/ex04/member/login");
			return false;
		}
	}

	private void saveDestination(HttpServletRequest request) {
		logger.info("==== saveDestination() 호출");
		String uri = request.getRequestURI();
		logger.info("요청 URI : " + uri);

		String contextRoot = request.getContextPath(); // contextRoot : /ex04
		logger.info("contextRoot : " + contextRoot);
		uri = uri.replace(contextRoot, "");

		String queryString = request.getQueryString();
		logger.info("QueryString : " + queryString);

		String targetURL = "";
		if (queryString == null) {
			targetURL = uri;
		} else {
			targetURL = uri + "?" + queryString;
		}
		logger.info("targetURL : " + targetURL);
		request.getSession().setAttribute("targetURL", targetURL);
	}
}
