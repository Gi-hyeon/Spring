package kr.or.ddit.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginController {

	@RequestMapping(value = "/login1", method = RequestMethod.GET)
	public String loginForm() {
		return "login/loginForm";
	}
	
	@RequestMapping(value = "/login1", method = RequestMethod.POST)
	public String login(String userId, String userPw, Model model) {
		
		MemberVO member = new MemberVO();
		member.setUserId(userId);
		member.setUserPw(userPw);
		member.setUserName("홍길동");
		
		model.addAttribute("user", member);
		
		return "login/success";
	}
	
}
