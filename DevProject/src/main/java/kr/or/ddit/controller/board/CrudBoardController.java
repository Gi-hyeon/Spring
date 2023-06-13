package kr.or.ddit.controller.board;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/crud/board")
@Slf4j
public class CrudBoardController {
	
	@Inject
	private IBoardService service;
	
	// 빈이 등록되고 초기화 단계에서 바로 확인할 때 사용
	@PostConstruct
	public void init() {
		log.info("aopProxy 상태(interface 기반) : {}" + AopUtils.isAopProxy(service));
		log.info("aopProxy 상태(class 기반) : {}" + AopUtils.isCglibProxy(service));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String crudRegisterForm(Model model) {
		log.info("CrudBoardController() 실행...!");
		
		model.addAttribute("board", new Board());
		
		return "crud/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String crudRegister(Model model, Board board) {
		log.info("crudRegister() 실행...!");
		
		try {
			service.register(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("msg", "등록이 완료되었습니다!");
		
		return "crud/success";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String crudList(Model model) {
		log.info("crudList() 실행...!");
		
		List<Board> boardList = service.list();
		model.addAttribute("boardList", boardList);
		
		return "crud/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String crudRead(Model model, int boardNo) {
		log.info("crudRead() 실행...!");
		
		Board board = service.read(boardNo);
		log.info("board : " + board);
		model.addAttribute("board", board);
		
		return "crud/read";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String crudModifyForm(Model model, int boardNo) {
		log.info("crudModifyForm() 실행...!");
		
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("status", "u");		// '수정을 진행중입니다' 라는 flag 값
		
		return "crud/register";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String crudModify(Model model, Board board) {
		log.info("crudModify() 실행...!");
		
		service.update(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		
		return "crud/success";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String crudDelete(Model model, int boardNo) {
		log.info("crudDelete() 실행...!");
		
		service.delete(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		
		return "crud/success";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String crudSearch(Model model, String title) {
		log.info("crudSearch() 실행...!");
		
		Board board = new Board();
		board.setTitle(title);
		
		List<Board> boardList = service.search(board);
		model.addAttribute("board", board);
		model.addAttribute("boardList", boardList);
		
		return "crud/list";
	}
}
















