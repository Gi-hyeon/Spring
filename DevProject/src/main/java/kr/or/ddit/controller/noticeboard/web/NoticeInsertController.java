package kr.or.ddit.controller.noticeboard.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String noticeInsert(NoticeVO noticeVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(noticeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		if (StringUtils.isBlank(noticeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if (errors.size() > 0) {	// 에러가 발생
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", noticeVO);
			goPage = "notice/form";
		} else {
			noticeVO.setBoWriter("a001");	// 임시로 넣어둔다. (로그인 처리 후 재 세팅할 예정)
			ServiceResult result = noticeService.insertNotice(noticeVO);
			if (result.equals(ServiceResult.OK)) {
				goPage = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
			} else {
				model.addAttribute("message", "서버 에러, 다시 시도해주세요!");
				goPage = "notice/form";
			}
		}
		
		return goPage;
	}
	
	// 요청URI : /notice/generalForm
	// 요청방식 : get
	@GetMapping("/generalForm")
	public String generalForm() {
		//forwarding
		return "notice/generalForm";
	}
	
	/*
	 * 요청 URI : /notice/generalFormPost                                    
	 * 요청 방식 : post   
	 * 요청파라미터 : {boTitle=제목입니다, boContent=내용입니다, boWriter=개똥이, boFile=파일객체}
	 * 
	 * @ResponseBody : json, but.. 텍스트로 리턴해줌
	 */
	
	@ResponseBody
	@PostMapping("/generalFormPost")
	public String generalFormPost(NoticeVO noticeVO) {
		/*
		 * NoticeVO(boNo=0, boTitle=title test, boContent=<p>content&nbsp;test</p>
		 * , boWriter=writer test, boDate=null, boHit=0, delNoticeNo=null, 
		 * boFile=[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@7a8d146e])
		 */
		log.info("noticeVO : " + noticeVO);
		
		// 파일을 업로드할 대상
		String uploadFolder = "D:\\A_TeachingMaterial\\6_JspSpring\\02.SPRING2\\workspace_spring2\\DevProject\\src\\main\\webapp\\resources\\upload";
		
		// 파일 객체 꺼내오기
		MultipartFile[] boFile = noticeVO.getBoFile();
		
		// 파일배열객체로부터 파일을 하나씩 꺼내오자
		for(MultipartFile multipartFile : boFile) {
			log.info("--------------------");
			log.info("upload file name : " + multipartFile.getOriginalFilename());
			log.info("upload file size : " + multipartFile.getSize());
			log.info("upload file contentType : " + multipartFile.getContentType());	//MIME 타입
			
			// File 객체 복사 설계(복사할 대상 경로, 파일명)
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			// 년/월/일 폴더 생성
			// UUID 처리(파일명 중복 방지)
			
			// 파일 복사 실행(파일원본.transferTo(설계))
			try {
				multipartFile.transferTo(saveFile);
				
				// 썸네일 생성
				return "Success";
			} catch (IllegalStateException | IOException e) {
				//e.printStackTrace();
				log.error(e.getMessage());
				return "Failed";
			}
		}
		
		// insert/update/delete의  경우 returnType = "int"를 생략
		noticeService.insertNotice(noticeVO);
		
		return "Success";
	}
}






























