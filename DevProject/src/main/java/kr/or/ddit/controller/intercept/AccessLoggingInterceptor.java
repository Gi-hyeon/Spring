package kr.or.ddit.controller.intercept;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessLoggingInterceptor extends HandlerInterceptorAdapter {

	PrintWriter writer;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle...!");
		File file = new File("C:/logs/ddit_logging.log");
		writer = new PrintWriter(new FileWriter(file, true), true);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("preHandle...!");
		
		String requestURI = request.getRequestURI();
		log.info("requestURI : " + requestURI);
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		Class clazz = method.getDeclaringClass();		// class kr.or.ddit.controller.BoardController와 같은 녀석
		
		String className = clazz.getName();				// kr.or.ddit.controller.BoardController와 같은 녀석
		String classSimpleName = clazz.getSimpleName();	// BoardController와 같은 녀석
		String methodName = method.getName();			// boardList와 같은 녀석
		
		writer.printf("현재 일시 : %s %n", getCurrentTime());
		writer.printf("Access Controller : %s %n", className + "." + methodName);
	}
	
	public String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		
		return formatter.format(cal.getTime());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("preHandle...!");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
























