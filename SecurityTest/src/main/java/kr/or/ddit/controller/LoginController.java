package kr.or.ddit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	// 에러가 발생하거나 로그아웃이 발생됐을 때 해당 URI로 요청이 전달되는데,
	// 이때 error 정보와 logout 정보를 활용하도록 한다.
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(String error, String logout, Model model) {
		log.info("loginForm() 실행...!");
		log.info("error : " + error);
		log.info("logout : " + logout);
		
		if (error != null) {
			model.addAttribute("error", "Login Error");
		}
		if (logout != null) {
			model.addAttribute("logout", "Logout!");
		}
		
		return "loginForm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutForm() {
		return "logoutForm";
	}
	
}
