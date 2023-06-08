package kr.or.ddit.controller.noticeboard.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.DDITMemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/notice")
@Slf4j
public class NoticeLoginController {

	@Inject
	private INoticeService noticeService;
	
	// 로그인 페이지 요청
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String noticeLogin(
			//@RequestParam(name = "stat", required = false, defaultValue = "0") int stat, 
			Model model) {
		// 메세지 처리를 쿼리 스트링에 특정 키값을 넘겨서 요청을 처리할 메서드에서 처리할 수 있다. (방법1)
		//if (stat == 1) {
		//	model.addAttribute("message", "회원가입을 완료하였습니다!");
		//}
		
		model.addAttribute("bodyText", "login-page");
		
		return "conn/login";
	}
	
	// 회원가입 페이지 요청
	@RequestMapping(value = "/signup.do", method = RequestMethod.GET)
	public String noticeRegister(Model model) {
		model.addAttribute("bodyText", "register-page");
		
		return "conn/register";
	}
	
	@ResponseBody
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public ResponseEntity<ServiceResult> idCheck(String memId) {
		ServiceResult result = noticeService.idCheck(memId);
		return new ResponseEntity<ServiceResult>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(
			HttpServletRequest req, 
			DDITMemberVO memberVO, 
			Model model, RedirectAttributes ra) {
		String goPage = "";
		
		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if (StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		if (StringUtils.isBlank(memberVO.getMemName())) {
			errors.put("memName", "이름을 입력해주세요.");
		}
		
		if (errors.size() > 0) {
			model.addAttribute("bodyText", "register-page");
			model.addAttribute("errors", errors);
			model.addAttribute("member", memberVO);
			goPage = "conn/register";
		} else {
 			ServiceResult result = noticeService.signup(req, memberVO);
			if (result.equals(ServiceResult.OK)) {
				ra.addFlashAttribute("message", "회원가입을 완료하였습니다!");	// 일회성 메세지
				goPage = "redirect:/notice/login.do?stat=1";
			} else {
				model.addAttribute("bodyText", "register-page");
				model.addAttribute("message", "서버에러, 다시 시도해주세요!");
				model.addAttribute("member", memberVO);
				goPage = "conn/register";
			}
		}
		
		return goPage;
	}
	
	// 아이디/비밀번호 찾기 페이지 요청
	@RequestMapping(value = "/forget.do", method = RequestMethod.GET)
	public String noticeLoginForget(Model model) {
		model.addAttribute("bodyText", "login-page");
		
		return "conn/forget";
	}
	
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(
			HttpServletRequest req,
			DDITMemberVO memberVO, Model model) {
		String goPage = "";
		
		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if (StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		
		if (errors.size() > 0) {
			model.addAttribute("bodyText", "login-page");
			model.addAttribute("errors", errors);
			model.addAttribute("member", memberVO);
			goPage = "conn/login";
		} else {
			DDITMemberVO member = noticeService.loginCheck(memberVO);
			if (memberVO != null) { 
				HttpSession session = req.getSession(); 
				session.setAttribute("SessionInfo", member);
				goPage = "redirect:/notice/list.do";
			} else {
				model.addAttribute("bodyText", "login-page");
				model.addAttribute("message", "서버에러, 로그인  정보를 정확하게 입력해주세요!");
				model.addAttribute("member", memberVO);
			}
		}
		
		return goPage;
	}
}
























