package kr.or.ddit.controller.file.item01;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.file.item01.service.ItemService;
import kr.or.ddit.vo.Item;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/item")
public class FileUploadController01 {
	/*
	 * 13장 파일 업로드
	 * 
	 * 1. 파일업로드 설명
	 * - 서블릿 3.0에서 지원하는 파일 업로드 기능과 스프링 웹에서 제공하는 컴포넌트를 사용하여 파일을 업로드한다.
	 * 
	 * 		파일 업로드 설정
	 * 		1) 서블릿 3.0 이상 지원 
	 * 		2) 서블릿 표준 파일 업로드 기능을 활성화
	 * 		3) 스프링 MVC와 연계
	 * 		4) 업로드 된 파일 저장 위치 설정
	 * 
	 * 		환경설정
	 * 		1) 의존 관계 정의
	 * 		- 파일을 처리하기 위해서 의존 라이브러리를 추가한다.
	 * 			> pom.xml commons-io, commons-fileupload
	 * 		2) 웹 컨테이너 설정
	 * 			> web.xml 서블릿 표준 버전 3.1로 설정
	 * 			> multipart-config 활성화(web.xml servlet 태그 내 설정)
	 * 		3) 스프링 웹 설정
	 * 			> servlet-context.xml
	 * 			> multipartResolver Bean 등록 (id는 multipartResolver로 설정)
	 * 			
	 * 			[ 파일 업로드 설정 ]
	 * 			파일 업로드를 설정하는 방식은 2가지가 있다.
	 * 			1. StandardServletMultipartResolver 사용 시 설정
	 * 				> Servlet 3.0의 part를 이용한 multipartFile 데이터 처리
	 * 				> Servlet-context.xml 에 설정
	 * 					> StandardServletMultipartResolver Bean 등록
	 * 				> web.xml에 설정
	 * 					> multipart-config (servlet 태그 안에 설정)
	 * 			2. CommonsMultipartResolver 사용 시 설정 (우리가 사용할 방식)
	 * 				> Commons Fileupload API를 이용한 Multipart File 데이터 처리
	 * 				> Bean 속성으로 maxUploadSize, maxInMemorySize, defaultEncoding 설정을 제공한다.
	 * 				> 기본 값 및 허용되는 값에 대한 자세한 내용은 DiskFileUpload 속성을 참조
	 * 				> pom.xml 설정
	 * 					> commons-fileupload 추가
	 * 				> root-context.xml에 설정
	 * 					> CommonsMultipartResolver bean 등록
	 * 
	 * 				> root-context.xml에 설정
	 * 					> uploadPath bean 등록
	 * 				
	 * 				> multipartFilter 필터 등록
	 * 					> web.xml
	 * 
	 * 		데이터베이스 준비
	 * 			1) item 테이블 생성(item, item2, item3, item3_attach)
	 * 
	 * 		1. 파일 업로드 구현 설명
	 * 
	 * 			- 파일업로드 등록 화면 컨트롤러 만들기 (FileUploadController01)
	 * 			- 파일업로드 등록 화면 메서드 만들기 (itemRegisterForm:get)
	 * 			- 파일업로드 등록 화면 만들기 (item/register.jsp)
	 * 			- 확인
	 * 
	 * 			- 파일업로드 등록 기능 컨트롤러 메서드 만들기(itemRegister:post)
	 * 			- 파일업로드 등록 기능 서비스 인터페이스 메서드 만들기
	 * 			- 파일업로드 등록 기능 서비스 클래스 메서드 만들기
	 * 			- 파일업로드 등록 기능 Mapper 인터페이스 메서드 만들기
	 * 			- 파일업로드 등록 기능 Mapper xml 쿼리 만들기
	 * 			- 파일업로드 등록 완료 페이지 만들기
	 * 			- 확인
	 * 
	 */
	
	// root-context.xml에서 설정한 uploadPath 빈등록 path 경로를 사용한다(value로 설정했던 값)
	@Resource(name = "uploadPath")
	private String resourcesPath;	
//	private String uploadPath = "C:\\upload";
	
	@Inject
	private ItemService itemService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String itemRegisterForm() {
		
		return "item/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String itemRegister(Item item, Model model) throws IOException {
		MultipartFile file = item.getPicture();
		
		log.info("originalName : " + file.getOriginalFilename());
		log.info("size : " + file.getSize());
		log.info("contentType : " + file.getContentType());
		
		// 넘겨받은 파일을 이용하여 파일 업로드 (복사)를 진행한다.
		// return : UUID + "_" + 원본 파일명
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		item.setPictureUrl(createdFileName);
		itemService.register(item);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		
		return "item/success";
	}
	
	
	private String uploadFile(String originalName, byte[] fileData) throws IOException {
		System.out.println("resourcesPath : " + resourcesPath);
		UUID uuid = UUID.randomUUID(); // UUID 파일명 생성 준비
		String createdFileName = uuid.toString() + "_" + originalName;	// UUID + "_" + 원본파일명
		
		File file = new File(resourcesPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		File target = new File(resourcesPath, createdFileName);
		FileCopyUtils.copy(fileData, target);	// 파일 복사
		
		return createdFileName;
	}
	
	
}















