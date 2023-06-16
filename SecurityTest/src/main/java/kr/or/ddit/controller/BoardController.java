package kr.or.ddit.controller;

import java.security.Principal;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	// 일반게시판 목록 화면
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		log.info("list() 실행...!");
		return "board/list";
	}
	
	// 일반게시판 등록 화면
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(Principal principal) {
		log.info("registerForm() 실행...!");
		
		// 방법1)
		log.info("사용자명 : " + principal.getName());
		
		// 방법2)
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("user.username : " + user.getUsername());
		log.info("user.password : " + user.getPassword());
		
		// 역할명 얻어오기
		Iterator<GrantedAuthority> ite = user.getAuthorities().iterator();
		while (ite.hasNext()) {
			log.info("auth : " + ite.next().getAuthority());
		}
		
		return "board/register";
	}
	
}
