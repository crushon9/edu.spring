package edu.spring.ex04.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor2 extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SampleInterceptor2.class);

	// * preHandle
	// 요청(request)에 해당하는 컨트롤러 메소드가 동작하기 전에 request을 가로채서 해야할 기능들을 작성
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { // 시작부터 끝까지 request와 response는 존재한다
		logger.info("===== preHandle 호출");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		logger.info("url 경로에 있는 bean 객체 : " + handlerMethod.getBean()); // HomeController (servlet-context.xml에서 설정했음)
		logger.info("url 경로에 있는 method 이름 : " + method.getName());

		return true; // preHandle() 리턴값의 의미
		// true : 원래 실행하려고 했던 컨트롤러 메소드 실행
		// false : 컨트롤러 메소드를 실행하지 않음
		// return super.preHandle(request, response, handler);
	}

	// * PostHandle
	// 컨트롤러 메소드가 수행된 이후에, DispatcherServlet이 view(JSP)를 처리하기 전에 해야할 기능들을 작성
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("===== postHandle 호출");
		// modelAndView 객체
		// Intercept된 경로에 있는 model과 view의 정보를 가져옴
		String data = (String) modelAndView.getModel().get("data");
		logger.info("data : " + data);
		super.postHandle(request, response, handler, modelAndView);
	}

	// * AfterCompletion
	// DispatcherServlet에 의해 view(JSP)처리가 끝난 후에 response를 보내기전 해야할 기능들을 작성
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("===== afterCompletion 호출");
		super.afterCompletion(request, response, handler, ex);
	}

}
