package kr.or.ddit.controller.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	// Alt + Shift + S -> Overi~~~ method
	
	private static final String USER_INFO = "userInfo";

	// 지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle...!");
		
		String requestURL = request.getRequestURL().toString();	// http://localhost/login1
		String requestURI = request.getRequestURI();
		
		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());	// kr.or.ddit.controller.login.LoginController@3213h123
		log.info("memethod : " + methodObj);	// public java.lang.String.kr.or.ddit.controller.login.LoginController.loginForm()
		
		
		// 키 'userInfo'를 가지고 있는 세션이 존재하면 삭제 처리로 초기화
		HttpSession session = request.getSession();
		if (session.getAttribute(USER_INFO) != null) {
			session.removeAttribute(USER_INFO);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle...!");
		
		String requestURL = request.getRequestURL().toString();	// http://localhost/login1
		String requestURI = request.getRequestURI();
		
		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());
		log.info("Method : " + methodObj);
		
		HttpSession session = request.getSession();
		
		// ModelAndView 객체가 들어가있음
		// 컨트롤러 메서드를 거쳤다가 postHandle로 넘어오면서 전달된 user라는 키에 value로 member가 담긴 값이
		// Model에 담겨져있다. 그 중에 'user'로 넘긴 값이 로그인 후 인증된 회원 1명이 정보가 담긴 MemberVO 자바빈즈 객체가 되고
		// 객체가 null이 아닌 경우 메인화면으로 리다이렉트 처리한다.
		ModelMap modelMap = modelAndView.getModelMap();
		Object member = modelMap.get("user");
		
		// 넘겨받은 데이터가 null이 아닌 경우 세션에 등록한다
		if (member != null) {
			log.info("member : " + member);
			log.info("member != null");
			
			session.setAttribute(USER_INFO, member);
			response.sendRedirect("/");
		}
	}

	// DispatcherServlet이 화면 처리가 완료된 상태에서 처리한다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion...!");
		
		String requestURL = request.getRequestURL().toString();	// http://localhost/login1
		String requestURI = request.getRequestURI();
		
		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());
		log.info("Method : " + methodObj);
		
		
	}
	
	
	
	
}
