package kr.or.ddit.controller.board;

import javax.inject.Inject;

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
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String crudRegisterForm(Model model) {
		log.info("CrudBoardController() 실행...!");
		
		model.addAttribute("board", new Board());
		
		return "crud/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String crudRegister(Model model, Board board) {
		log.info("crudRegister() 실행...!");
		
		service.register(board);
		
		model.addAttribute("msg", "등록이 완료되었습니다!");
		
		return "crud/success";
	}
	
	
	
}
