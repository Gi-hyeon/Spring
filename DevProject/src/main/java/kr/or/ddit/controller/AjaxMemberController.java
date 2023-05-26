package kr.or.ddit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxMemberController {
	
	// Ajax 방식 요청 처리를 위한 페이지
	@RequestMapping(value = "/registerForm", method = RequestMethod.GET)
	public String ajaxRegisterForm() {
		log.info("ajaxRe`gisterForm() 실행!!!");
		return "member/ajaxRegisterForm";
	}
	
	// 1) URL 경로 상의 경로 변수값을 @PathVariable 어노테이션을 지정하여 문자열 매개변수로 처리한다.
	@RequestMapping(value = "/register/{userId}")
	public ResponseEntity<String> ajaxRegister01(@PathVariable String userId) {
		log.info("ajaxRegister01() 실행!!!");
		log.info("userId : " + userId);
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		
		return entity;
	}
	
	// 2) URL 경로 상의 여러개의 경로 변수값을 @PathVariable 어노테이션을 지정하여 여러개의 문자열 매개변수로 처리한다.
	@RequestMapping(value = "/register/{userId}/{password}", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister02(
 			@PathVariable("userId") String userId,
 			@PathVariable("password") String password) {
		log.info("ajaxRegister02() 실행!!!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	
	// 3) 객체 타입의 JSON 요청 데이터 @RequestBody 어노테이션을 지정하여 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value = "/register03", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister03(
			@RequestBody Member member) {
		log.info("ajaxRegister03() 실행!!!");
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 4) 객체 타입의 JSON 요청 데이터 문자열 매개변수로 처리할 수 없다.
	@RequestMapping(value = "/register04", method = RequestMethod.POST)
	public ResponseEntity<String> register04(String userId) {	//@RequesBody를 입력해야 값을 받을 수 있다. 비동기식
		// 요청 본문에서 데이터가 바인딩 되지 않아 userId가 null 나온다.
		// 요청 본문에서 데이터를 가져오려면 @RequestBody 어노테이션을 꼭 붙여주어야한다.
		log.info("register04() 실행!!!");
		log.info("userId : " + userId);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 5) 요청 URL에 쿼리파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.
	@RequestMapping(value = "/register05", method = RequestMethod.POST)
	public ResponseEntity<String> register05(String userId, String password) {
		// userId는 쿼리스트링에 담겨져 오는 데이터이기 때문에, 일반적인 방식으로도 데이터를 받을 수 있지만,
		// passWord는 요청본문에서 데이터를 바인딩해 받아오지 못하므로 null이 출력된다.
		log.info("register05() 실행!!!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 6) 객체 타입의 JSON 요청 데이터를 @PathVariable 어노테이션을 지정하 문자열 매개변수와 @RequestBody 어노테이션을 지정한 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value = "/register06/{userId}", method = RequestMethod.POST)
	public ResponseEntity<String> register06(
			@PathVariable("userId") String userId, 
			@RequestBody Member member) {
		log.info("register06() 실행!!!");
		log.info("userId : " + userId);
		log.info("member.getUserId : " + member.getUserId());
		log.info("member.getPassword : " + member.getPassword());
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 7) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렉션 매개변수에 @RequestBody 어노테이션을 지정하여 처리한다.
	@RequestMapping(value = "/register07", method = RequestMethod.POST)
	public ResponseEntity<String> register07(
			@RequestBody List<Member> memberList) {
		// 비동기 처리시, List 컬렉션으로 데이터를 받을때에는 @RequestBody라는 어노테이션을 이용하여 바인딩한다.
		// 동기 처리시에는 컨트롤러 메서드 내에서 List타입으로 값을 바인딩할 수 없지만, 객체 내 존재하는 List타입으로는 데이터를 바인딩할 수 있다.
		// 동기와 비동기처리 시의 차이점을 꼭 알아두자
		log.info("register07() 실행!!!");
		
		for(Member member : memberList) {
			log.info("member.getUserId : " + member.getUserId());
			log.info("member.getPassword : " + member.getPassword());
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 8) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value = "/register08", method = RequestMethod.POST)
	public ResponseEntity<String> register08(
			@RequestBody Member member) {
		log.info("register08() 실행!!!");
		
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		
		Address address = member.getAddress();
		
		if (address != null) {
			log.info("address.getPostCode : " + address.getPostCode());
			log.info("address.getLocation : " + address.getLocation());
		} else {
			log.info("address is null");
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 9) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value = "/register09", method = RequestMethod.POST)
	public ResponseEntity<String> register09(
			@RequestBody Member member) {
		log.info("register09() 실행!!!");

		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		
		List<Card> cardList = member.getCardList();
		
		if (cardList != null) {
			log.info("cardList.size : " + cardList.size());
			
			for(Card card : cardList) {
				log.info("card.getNo : " + card.getNo());
				log.info("card.getValidMonth : " + card.getValidMonth());
			}
		} else {
			log.info("cardList is null");
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}




























