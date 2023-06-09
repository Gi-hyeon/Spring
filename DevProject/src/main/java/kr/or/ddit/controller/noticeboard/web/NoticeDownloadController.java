package kr.or.ddit.controller.noticeboard.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.controller.noticeboard.view.NoticeDownloadView;
import kr.or.ddit.vo.NoticeFileVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeDownloadController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value = "/download.do")
	public View noticeDownloadProcess(int fileNo, Model model) {
		NoticeFileVO noticeFileVO = noticeService.noticeDownload(fileNo);
		Map<String, Object> noticeFileMap = new HashMap<String, Object>();
		noticeFileMap.put("fileName", noticeFileVO.getFileName());
		noticeFileMap.put("fileSize", noticeFileVO.getFileSize());
		noticeFileMap.put("fileSavepath", noticeFileVO.getFileSavepath());
		
		model.addAttribute("noticeFileMap", noticeFileMap);
		
		/*
		 * 리턴되는 NoticeDownloadView는 jsp 페이지로 존재하는 페이지 Name을 요청하는 게 아니라
		 * 클래스를 요청하는 것인데 해당 클래스가 스프링에서 제공하는 abstractView 클래스를 상속받는 클래스인데
		 * 그 클래스는 AbstractView를 상속받아 renderMergeOutputModel 함수를 재정의할 때 View로 취급될 수 있게 해준다
		 */
		return new NoticeDownloadView();
	}
	
}
