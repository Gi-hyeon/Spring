package kr.or.ddit.board.web;

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
		log.info("totalRecord : " + totalRecord);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardService.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
		log.info("dataList : " + dataList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "pages/ddit_list";
	}
	
	@RequestMapping(value = "/dditDetail", method = RequestMethod.GET)
	public String dditDetail(int boNo, Model model) {
		log.info("boNo : " + boNo);
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
		
		log.info("getMem_id : " + vo.getMem_id());
		log.info("getMem_pw : " + vo.getMem_pw());
		
		
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
}



















