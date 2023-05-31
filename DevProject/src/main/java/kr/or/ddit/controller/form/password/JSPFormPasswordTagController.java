package kr.or.ddit.controller.form.password;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/formtag/password")
@Slf4j
public class JSPFormPasswordTagController {

	/*
	 * 8장. 스프링 폼 태그
	 * 
	 * 5. 패스워드 필드 요소
	 * - HTML 패스워드 필드를 출력하려면 <form:password> 요소를 사용한다.
	 */
	
	// 1) 모델에 기본생성자로 생성한 폼 객체를 추가한 후에 화면에 전달한다.
	@RequestMapping(value = "/registerForm01", method = RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행...!");
		
		// 파라미터로 바인딩 방법, 어노테이션을 이용한 방법
		model.addAttribute("member", new Member());
		
		return "form/password/registerForm01";
	}
	
	@RequestMapping(value = "/registerForm02", method = RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02() 실행...!");
		
		// 값을 설정해서 뷰에 전달하더라도 패스워드 필드에 반영되지 않는다.(패스워드는 바인딩이 안됨!!)
		Member member = new Member();
		member.setPassword("11223344");
		model.addAttribute("member", member);
		
		return "form/password/registerForm01";
	}
	
}
