package kr.or.ddit.board.web;

import java.io.Console;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Inject
	private IBoardService boardService;
	
	
	@RequestMapping(value = "/dditList")
	public String dditList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage, 
			@RequestParam(required = false, defaultValue = "title") String searchType, 
			@RequestParam(required = false) String searchWord, 
			Model model) {
		log.info("List실행...");
		
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		if (StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		// 목록 총 게시글 수 가져오기
		int totalRecord = boardService.selectBoardCount(pagingVO);
		//log.info("totalRecord : " + totalRecord);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardService.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
		//log.info("dataList : " + dataList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "pages/ddit_list";
	}
	
	@RequestMapping(value = "/dditDetail")
	public String dditDetail(int boNo, Model model) {
		log.info("dditDetail() 실행...");
		// log.info("boNo : " + boNo);
		
		BoardVO board = new BoardVO();
		
		boardService.boHitIncrement(boNo);
		board = boardService.selectBoardByboNo(boNo);
		
		model.addAttribute("board", board);
		
   		// log.info("board : " + board);
		
		return "pages/ddit_detail";
	}
	
	@RequestMapping(value = "/dditForm", method = RequestMethod.GET)
	public String dditForm() {
		
		return "pages/ddit_form";
	}
	
	@RequestMapping(value = "/dditSignin", method = RequestMethod.GET)
	public String dditSignin() {
		return "pages/ddit_signin";
	}
	
	@RequestMapping(value = "/dditSignup", method = RequestMethod.GET)
	public String dditSignup() {
		return "pages/ddit_signup";
	}
	
	@RequestMapping(value = "/loginSucces.do", method = RequestMethod.POST)
	public String loginSucces(MemberVO vo, Model model) {
		log.info("loginSucces() 실행...");
		
		//log.info("getMem_id : " + vo.getMem_id());
		//log.info("getMem_pw : " + vo.getMem_pw());
		
		
		MemberVO member = boardService.loginSuccess(vo);
		String goPage = "";
		HttpSession session = request.getSession();
		
		if (member != null) {
			log.info("Result : " + member);
			session.setAttribute("member", member);
			goPage = "redirect:/dditList";
		} else {
			log.info("로그인 실패...");
			model.addAttribute("msg", "1");
			goPage = "pages/ddit_signin";
		}
		
		
		return goPage;
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout() {
		HttpSession session = request.getSession();
		
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/dditSignin";
	}
	
	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.POST)
	public String boardInsert(String mem_id, BoardVO board, Model model) {
		log.info("boardInsert() 실행...");
		
		String goPage = "";
		
		//log.info("mem_id : " + mem_id);
		//log.info("board.getBoTitle : " + board.getBoTitle());
		//log.info("board.getBoContent : " + board.getBoContent());
		
		
		board.setBoWriter(mem_id);
		
		ServiceResult result = boardService.boardInsert(board);
		
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/dditDetail?boNo=" + board.getBoNo();
		} else {
			model.addAttribute("board", board);
			goPage = "pages/ddit_form";
		}
		
		return goPage;
	}
	
	@RequestMapping(value = "/boardDelete.do", method = RequestMethod.POST)
	public String boardDelete(Model model, int boNo) {
		//log.info("boNo : " + boNo);
		String goPage = "";
		
		ServiceResult result = boardService.boardDelete(boNo);
		
		
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/dditList";
		} else {
			model.addAttribute("deleteErr", "삭제 실패! 다시 시도해주세요.");
			
			goPage = "forward:/dditDetail?boNo=" + boNo;
		}
		
		return goPage;
	}
	
	@RequestMapping(value = "/boardUpdateForm.do", method = RequestMethod.GET)
	public String boardUpdateForm(int boNo, Model model) {
		log.info("boardUpdateForm() 실행...");
		
		log.info("boNo : " + boNo);
		
		BoardVO board = boardService.selectBoardByboNo(boNo);
		
		model.addAttribute("board", board);
		model.addAttribute("status", "u");
		
		return "forward:/dditForm";
	}
	
	@RequestMapping(value = "/boardUpdate.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO board, Model model) {
		log.info("boardUpdate() 실행...");
		String goPage = "";
		
		// log.info("getBoTitle : " + board.getBoTitle() );
		// log.info("getBoContent : " + board.getBoContent() );
		// log.info("getBoNo : " + board.getBoNo() );
		
		
		ServiceResult service = boardService.boardUpdate(board);
		
		log.info("service : " + service);
		
		if (service.equals(ServiceResult.OK)) {
			goPage = "redirect:/dditDetail?boNo=" + board.getBoNo();
		} else {
			model.addAttribute("board", board);
			model.addAttribute("status", "u");
			goPage = "pages/ddit_form";
		}
		
		return goPage;
	}
	
	@RequestMapping(value = "/userReigster.do", method = RequestMethod.POST)
	public String userReigster(MemberVO member, Model model) {
		log.info("userReigster() 실행...");
		String goPage = "";
		
		
//		log.info("getMem_id : " + member.getMem_id());
//		log.info("getMem_pw : " + member.getMem_pw());
//		log.info("getMem_name : " + member.getMem_name());
//		log.info("getMem_gender : " + member.getMem_gender());
//		log.info("getMem_phone : " + member.getMem_phone());
//		log.info("getMem_email : " + member.getMem_email());
//		log.info("getMem_agree : " + member.getMem_agree());
		
		if (StringUtils.isBlank(member.getMem_id()) || StringUtils.isBlank(member.getMem_pw()) || StringUtils.isBlank(member.getMem_name()) ||
				StringUtils.isBlank(member.getMem_gender()) || StringUtils.isBlank(member.getMem_phone()) || StringUtils.isBlank(member.getMem_email()) ||
				StringUtils.isBlank(member.getMem_agree())) {
			model.addAttribute("registerErr", "누락된 정보가 존재합니다!");
			model.addAttribute("member", member);
			
			goPage = "pages/ddit_signup";
		} else {
			ServiceResult service = boardService.userRegister(member);
			
			if (service.equals(ServiceResult.OK)) {
				model.addAttribute("succesCheck", "가입이 완료되었습니다.");
				goPage = "pages/ddit_signin";
			} else {
				model.addAttribute("succesCheck", "가입이 실패했습니다. 다시 시도해주세요");
				model.addAttribute("member", member);
				goPage = "pages/ddit_signup";
			}
		}
		
		return goPage;
	}
}



















