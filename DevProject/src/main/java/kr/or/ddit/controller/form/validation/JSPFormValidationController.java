package kr.or.ddit.controller.form.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/validation")
@Slf4j
public class JSPFormValidationController {
	/*
	 * 8장 스프링 폼 태그
	 * 
	 * 15. 입력값 검증 에러
	 * - 입력값 검증 에러를 출력하려면 <form:errors> 요소를 사용한다.
	 */
	@RequestMapping(value = "/registerFormValidation01", method = RequestMethod.GET)
	public String registerFormValidation01(Model model) {
		log.info("registerFormValidation01() 실행...!");
		
		model.addAttribute("member", new Member());
		
		return "form/validation/registerFormValidation01";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String registerFormValidationResult(Model model, Member member) {
		log.info("registerFormValidationResult() 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		log.info("eamil : " + member.getEmail());
		
		return "form/validation/result";
	}
}
