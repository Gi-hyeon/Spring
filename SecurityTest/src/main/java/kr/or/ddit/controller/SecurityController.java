package kr.or.ddit.controller;

public class SecurityController {
	/*
	 * 18장. 스프링 시큐리티
	 * 
	 * 1. 스프링 시큐리티 소개
	 * - 애플리케이션에서 보안 기능을 구현하는데 사용되는 프레임 워크이다.
	 * 	스프링 시큐리티는 필터 기반으로 동작하기 때문에 스프링 MVC와 분리되어 동작한다.
	 * 
	 * 		기본 보안 기능
	 * 		- 인증(Authentication)
	 * 			> 애플리케이션의 사용자의 정당성을 확인한다.
	 * 		- 인가(Authentization)
	 * 			> 애플리케이션의 리소스나 처리에 대한 접근을 제어한다.
	 * 
	 * 		시큐리티 제공 기능
	 * 		- 세션관리
	 * 		- 로그인 처리
	 * 		- CSRF 토큰 처리
	 * 		- 암호화 처리
	 * 		- 자동 로그인
	 * 
	 * 			** CSRF 용어 설명
	 * 			- 크로스 사이트 요청 위조는 웹 사이트 취약점 공격의 하나로, 사용자가 자신의 의지와는 무관하게 공격자가
	 * 			   위도한 행위(수정, 삭제, 등록 등)을 특정 웹 사이트에 요청하게 하는 공격을 말한다.
	 * 
	 * 2. 스프링 시큐리티 설정
	 * 	
	 * 		환경 설정
	 * 		- 의존 라이브러리 설정
	 * 			> 스프링 시큐리티가 제공하는 라이브러리를 추가한다. (pom.xml)
	 * 
	 * 			- spring-security-web
	 * 			- spring-security-config
	 * 			- spring-security-core
	 * 			- spring-security-taglibs
	 * 
	 * 		- 웹 컨테이너 설정
	 * 			> 스프링 시큐리티가 제공하는 서블릿 필터 클래스를 서블릿 컨테이너에 등록한다. (web.xml)
	 * 			> 스프링 시큐리티는 필터 기반이므로 시큐리티 필터체인을 등록한다.
	 * 				- param 추가 (/WEB-INF/spring/security-context.xml)
	 * 				- filter 추가 (web.xml)
	 * 
	 * 		- 스프링 시큐리티 설정
	 * 			> 스프링 시큐리티 컴포넌트를 빈으로 정의한다.
	 * 			> spring/security-context.xml 설정
	 * 
	 * 		스프링 시큐리티 기본 테스트 정책(웹 화면 접근 정책 ::: 테스트를 위한 정책이므로 스프링 시큐리티 정책과는 무관)
	 * 			> 웹 화면 접근 정책을 말한다.(테스트용)
	 * 
	 * 			대상		│	화면		│	접근 정책
	 * 		──────────────────────────────────────────────────
	 * 		일반 게시판		│	목록화면	│	모두가 접근 가능합니다.
	 * 					│	등록화면	│	로그인한 회원만 접근 가능합니다.
	 * 		──────────────────────────────────────────────────
	 * 		공지 게시판		│	목록화면	│	모두가 접근 가능합니다.
	 * 					│	등록화면	│	로그인한 관리자만 접근 가능합니다.
	 * 		──────────────────────────────────────────────────
	 * 
	 * 		화면 설명
	 * 		- 컨트롤러
	 * 			> security.SecurityBoardController
	 * 			> security.SecurityNoticeController
	 * 
	 * 		- 화면 페이지
	 * 			> security/board/list
	 * 			> security/board/register
	 * 			> security/notice/list
	 * 			> security/notice/register
	 * 		
	 * 			** 화면에 맞는 컨트롤러와 페이지를 작성한다.
	 * 
	 * 		화면 요청
	 * 			> localhost/security/board/list
	 * 			> localhost/security/board/register
	 * 			> localhost/security/notice/list
	 * 			> localhost/security/notice/register
	 * 
	 * 3. 접근 제한 설정
	 * 	- 시큐리티 설정을 통해서 특정 URI에 대한 접근을 제한할 수 있다.
	 */
}







































