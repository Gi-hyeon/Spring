package kr.or.ddit.controller.database;

public class MybatisController {
	/*
	 * 12장 마이바티스
	 * 
	 * 1. 마이바티스란?
	 * 
	 * 		1) 뭘까?
	 * 		마이바티스는 자바 퍼시스턴스 프레임워크의 하나로 XML 서술자나 어노테이션을 사용하여 저장프로시저나 SQL문으로 객체들을 연결시킨다.
	 * 		마이바티스는 Apache 라이선스 2.0으로 배포되는 자유 소프트웨이다.
	 * 		
	 * 		2) 마이바티스를 사용함으로써 얻을 수 있는 이점
	 * 		- SQL의 체계적인 관리
	 * 		- 자바 객체와 SQL 입출력 값이 투명한 바인딩
	 * 		- 동적 SQL 조합
	 * 		
	 * 		3) 마이바티스 설정
	 * 			3-1) 의존 관계 정의
	 * 			- 총 6가지 라이브러리를 등록하여 관계를 정의한다.	(DatabaseConnectController 참고)
	 * 			3-2) 스프링과 마이바티스 연결 설정
	 * 			- root-context.xml 설정(DatabaseConnectController 참고)
	 * 			- 총 3가지를 등록하여 설정한다. (추가로 Mapper를 등록하기 위한 basePackage 정보도 함께 추가한다.)
	 * 			3-3) 마이바티스 설정
	 * 			- webapp/WEB-INF/mybatisAlias/mybatisAlias.xml 설정
	 * 			- 마이바티스위 위치 설정은 root-context.xml의 'sqlSessionFactory' 설정 시, property 요소로 적용한다.
	 * 
	 * 		4) 관련 테이블 생성
	 * 			4-1) board 테이블 생성 
	 * 			4-2) member 테이블 생성
	 * 			4-3) member_auth 테이블 생성
	 * 
	 * 2. Mapper 인터페이스
	 * - 인터페이스의 구현을 mybatis-spring에서 자동으로 생성할 수 있다.
	 * 
	 *  	1) 마이바티스 구현
	 *  		
	 *  		1-1) Mapper 인터페이스
	 * 			- BoardMapper.java 생성 (인터페이스)
	 *  		
	 *  		1-2) Mapper 인터페이스와 매핑할 Mapper
	 * 			- sqlmap/boardMapper_SQL.xml 생성
	 * 
	 *  		1-3) 게시판 구현 설명
	 * 			- 게시판 컨트롤러 만들기 (board/CrudBoardController)
	 * 			- 게시판 등록 화면 컨트롤러 메서드 만들기(crudRegister:get)
	 * 			- 게시판 등록 화면 만들기(crud/register.jsp)
	 * 			- 확인
	 * 
	 * 			- 게시판 등록 기능 컨트롤러 메서드 만들기(crudRegister:post)
	 * 			- 게시판 등록 기능 서비스 인터페이스 메서드 만들기
	 * 			- 게시판 등록 기능 서비스 클래스 메서드 만들기
	 * 			- 게시판 등록 기능 Mapper 인터페이스메서드 만들기
	 * 			- 게시판 등록 기능 Mapper xml 쿼리 만들기
	 * 			- 게시판 등록 완료 페이지 만들기
	 * 			- 확인
	 * 
	 * 			- 게시판 목록 화면 컨트롤러 메서드 만들기(crudList:get)
	 * 			- 게시판 목록 화면 서비스 인터페이스 메서드 만들기
	 * 			- 게시판 목록 화면 서비스 클래스 메서드 만들기 
	 * 			- 게시판 목록 화면 Mapper 인터페이스 메서드 만들기
	 * 			- 게시판 목록 화면 Mapper xml 쿼리 만들기 
	 * 			- 게시판 목록 화면 만들기(crud/list.jsp)
	 * 			- 확인
	 * 
	 * 			- 게시판 상세 화면 컨트롤러 메서드 만들기(crudRead:get)
	 * 			- 게시판 상세 화면 서비스 인터페이스 메서드 만들기
	 * 			- 게시판 상세 화면 서비스 클래스 메서드 만들기
	 * 			- 게시판 상세 화면 Mapper 인터페이스 메서드 만들기
	 * 			- 게시판 상세 화면 Mapper xml 쿼리 만들기
	 * 			- 게시판 상세 화면 만들기(crud/read.jsp)
	 * 			- 확인
	 * 			
	 * 			- 게시판 수정 화면 컨트롤러 메서드 만들기(crudModify:get)
	 * 			- 게시판 수정 화면 서비스 인터페이스 메서드 만들기
	 * 			- 게시판 수정 화면 서비스 클래스 메서드 만들기
	 * 			- 게시판 수정 화면 Mapper 인터페이스 메서드 만들기
	 * 			- 게시판 수정 화면 Mapper xml 쿼리 만들기
	 * 			- 게시판 수정 화면 만들기(crud/register.jsp)
	 * 			- 게시판 수정 기능 컨트롤러 메서드 만들기(crudModify:post)
	 * 			- 게시판 수정 기능 서비스 클래스 메서드 만들기
	 * 			- 게시판 수정 기능 Mapper 인터페이스 메서드 만들기
	 * 			- 게시판 수정 기능 Mapper xml 쿼리 만들기
	 * 			- 확인 
	 * 
	 * 			- 게시판 삭제 기능 컨트롤러 메서드 만들기(crudRemove:post)
	 * 			- 게시판 삭제 기능 서비스 인터페이스 메서드 만들기
	 * 			- 게시판 삭제 기능 서비스 클래스 메서드 만들기
	 * 			- 게시판 삭제 기능 Mapper 인터페이스 메서드 만들기
	 * 			- 게시판 삭제 기능 Mapper xml 쿼리 만들기
	 * 			- 확인 
	 * 
	 * 			- 게시판 목록 화면 검색 페이지 추가 (crud/board/list.jsp)
	 * 			- 게시판 검색 기능 컨트롤러 메서드 추가 (crudSearch:post)
	 * 			- 게시판 검색 기능 서비스 인터페이스 메서드 추가
	 * 			- 게시판 검색 기능 서비스 클래스 메서드 추가
	 * 			- 게시판 검색 기능 Mapper 메서드 추가
	 * 			- 게시판 검색 기능 Mapper xml 쿼리 추가
	 * 			- 확인
	 * 
	 * 			- 기본 CRUD 끝
	 * 
	 * 3. 별칭 적용
	 * - TypeAlias로 매핑 파일에서 반복적으로 사용될 패키지의 이름을 정의한다.
	 * 
	 * 		1) 마이바티스 설정
	 * 
	 * 			1-1) mybatisAlias.xml 설정
	 * 				- typeAlias 설정을 한다.
	 * 
	 * 			1-2) boardMapper_SQL.xml의 type의 설정을 별칭으로 설정
	 * 				- mybatisAlias가 설정되어 있지 않는 경우엔 타입으로 설정하고자 하는 타입 형태를 패키지명이 포함되어 있는 구조로 설정해야한다.
	 * 				- 쿼리 태그에 각각 셋팅한 패키지명 대신 alias로 설정한 별칭으로 대체한다.
	 * 
	 * 4. '_'로 구분된 컬럼명 자동 매핑
	 * - 마이바티스 설정의 mapUnderscoreToCamelCase 프로퍼티 값을 true로 지정하면 '_'로 구분된 컬럼명을 소문자 낙타표기법의
	 * 		프로퍼티명으로 자동 매핑할 수 있다.
	 * 
	 * 		'_'가 포함되어 있는 데이터베이스 컬럼명을 Camel 기법 세팅으로 인해서 bo_no가 boNo로 처리된다.
	 * 
	 * 		1) 마이바티스 설정
	 * 
	 * 			1-1) mybatisAlias.xml 설정
	 * 				- <settings>
	 * 				- 	<setting name="mapUnderscoreToCamelCase" value="true"/> 설정 추가
	 * 				- </settings>
	 * 
	 * 			1-2) 매핑 파일 수정(기존 Mapper xml에 설정된 컬럼은 board_no와 같은 형태로 되어있다고 가정)
	 * 				- as boardNo, as regDate로 컬럼명을 대체하는 alias가 설정되어 있는 경우엔 as 부분을 삭제
	 * 
	 * 5. 기본키 취득
	 * - 마이바티스는 useGeneratedKeys 속성을 이용하여 insert할 때 데이터베이스 측에서 채변된 기본키를 취득할 수 있다.
	 * 
	 * 		1) 마이바티스 설정
	 * 
	 * 			1-1) 매핑 파일 수정(boardMapper_SQL.xml)
	 * 			- create 부분의 속성 추가
	 * 				> useGeneratedKeys="true" keyProperty="boardNo" 속성을 추가로 사용
	 * 				> <selectKey keyProperty="boardNo" resultType="int" order="BEFORE"> 
	 * 				> 	select seq_board.nextval from dual
	 * 				> </selectKey>
	 * 				> insert into board(
	 * 				> 	board_no, title, content, writer, reg_date
	 * 				> )values(
	 * 				> 	#{boardNo}, #{title}, #{content}, #{writer}, sysdate
	 * 				> )
	 * 
	 * 				아래 insert 쿼리가 실행 되기 전 selectkey태그 내에 있는 쿼리가 먼저 실행되어 최신의 boardNo를 생성하고
	 * 				생성된 boardNo를 sql쿼리가 담겨있는 insert 태그까지 넘어올 때 넘겨주고 있는 파라미터(board)의 property인
	 * 				boardNo에 세팅한다. 세팅된 boardNo를 아래에서 insert 시 등록 값으로 사용하고 board라는 자바빈즈 객체에 담겨
	 * 				최종 컨트롤러까지 넘어간다.
	 * 				
	 * 				*** seq_board.nextval대신 현재의 seq번호를 가져오기 위해 currval를 사용하게 되는 경우가 있다.
	 * 					이때 사용시 주의사항이 있다.
	 * 
	 * 					- select seq_board.currval from dual
	 * 					> 위 select 쿼리를 사용시, currval를 사용하는 데 있어서 사용 불가에 대한 에러가 발생할 수 있다.
	 * 					   currval를 사용할 때는 select seq_board.nextval from dual로 먼저 최초로 실행 후,
	 * 					   select seq_board.currval from dual로 사용하면 에러가 없음
	 * 					     같은 세션내에서의 실행이 워리짖 않았기 때문에 currval로 데이터를 가져오는데 에러가 발생한다.
	 * 					   
	 * 					# 그럼에도 나는 가져와야겠다! 다 필요없고 걍 내놔! 하고 싶으면
	 * 					> select last_number from user_sequences where sequence_name = '시퀀스명';
	 * 					> select last_number from user_sequences where sequence_name = 'seq_board';
	 * 					쿼리로 가져오면 된다. (권고사항 x)
	 * 					   
	 * 			2-2) 컨트롤러 메서드에서 crudRegister 부분의 등록이 되고나서 리턴되는 방향성을 수정
	 * 			- 지금은 완료페이지로 전환되지만, board에 담겨있는 boardNo를 이용하여 상세보기 페이지로 전환해도 무방!
	 * 
	 * 6. 동적 SQL
	 * - 마이바티스는 동적 SQL을 조립하는 구조를 지원하고 있으며, SQL 조립 규칙을 매핑파일에 정의할 수 있다.
	 * 	
	 * 		1) 동적으로 SQL을 조립하기 위한 SQL 요소
	 * 			- <where>
	 * 			> where 절 앞 뒤에 내용을 더 추가하거나 삭제할 때 사용하는 요소   
	 * 			- <if>
	 * 			> 조건을 만족할 때만 SQL을 조립할 수 있게 만드는 요소
	 * 			- <choose>
	 * 			> 여러 선택 항목에서 조건에 만족할 때만 SQL을 조립할 수 있게 만드는 요소
	 * 			- <foreach>
	 * 			> 컬렉션이나 배열에 대해 반복 처리를 하기위한 요소
	 * 			- <set>
	 * 			> set 절 앞뒤에 내용을 더 추가하거나 삭제할 때 사용하는 요소	
	 * 
	 * 7. 일대다 관계 테이블 매핑
	 * - 마이바티스 기능을 활용하여 매핑 파일을 적절하게 정의하면 일대다 관계 테이블 매핑을 쉽게 처리할 수 있다.
	 * 
	 * 		1) 게시판 구현 설명
	 * 
	 * 			- 회원 등록 화면 컨트롤러 만들기(member/CrudMemberController)
	 * 			- 회원 등록 화면 컨트롤러 메서드 만들기(crudMemberRegisterForm:get)
	 * 			- 회원 등록 화면 만들기(crud/member/register.jsp)
	 * 			- 확인
	 * 
	 * 			- 회원 등록 기능 컨트롤러 메서드 만들기(crudMemberRegister:post)
	 * 			- 회원 등록 기능 인터페이스 메서드 만들기
	 * 			- 회원 등록 기능 기능 클래스 메서드 만들기
	 * 			- 회원 등록 기능 Mapper 인터페이스 메서드 만들기
	 * 			- 회원 등록 기능 Mapper xml 쿼리 만들기
	 * 			- 회원 등록 기능 완료 페이지 만들기(crud/member/success.jsp)
	 * 			- 확인
	 * 
	 * 			- 회원 목록 화면 컨트롤러 메서드 만들기(crudMemberList:get)
	 * 			- 회원 목록 화면 서비스 인터페이스 메서드 만들기
	 * 			- 회원 목록 화면 서비스 클래스 메서드 만들기
	 * 			- 회원 목록 화면 Mapper 인터페이스 메서드 만들기  
	 * 			- 회원 목록 화면 Mapper xml 쿼리 만들기     
	 * 			- 회원 목록 화면 페이지 만들기(crud/member/list.jsp)
	 * 			- 확인 
	 * 
	 * 			- 회원 상세화면 컨트롤러 메서드 만들기(crudMemberRead:get)
	 * 			- 회원 상세화면 서비스 인터페이스 메서드 만들기        
	 * 			- 회원 상세화면 서비스 클래스 메서드 만들기          
	 * 			- 회원 상세화면 Mapper 인터페이스 메서드 만들기     
	 * 			- 회원 상세화면 Mapper xml 쿼리 만들기        
	 * 			- 회원 상세화면 만들기(crud/member/read.jsp)
	 * 			- 확인 
	 * 
	 * 			- 화면 수정 화면 컨트롤러 메서드 만들기(crudMemberModifyForm:get)
	 * 			- 화면 수정 화면 서비스 인터페이스 메서드 만들기         
	 * 			- 화면 수정 화면 서비스 클래스 메서드 만들기           
	 * 			- 화면 수정 화면 Mapper 인터페이스 메서드 만들기      
	 * 			- 화면 수정 화면 Mapper xml 쿼리 만들기         
	 * 			- 화면 수정 화면 페이지 만들기(crud/member/modify.jsp)
	 * 			- 확인 
	 * 
	 * 			- 회원 수정 기능 컨트롤러 메서드 만들기(crudMemberModify:post)  
	 * 			- 회원 수정 기능 서비스 인터페이스 메서드 만들기                       
	 * 			- 회원 수정 기능 서비스 클래스 메서드 만들기                         
	 * 			- 회원 수정 기능 Mapper 인터페이스 메서드 만들기                    
	 * 			- 회원 수정 기능 Mapper xml 쿼리 만들기                       
	 * 			- 회원 수정 완료 페이지 만들기 (위에서 이미 만듬)
	 * 			- 확인
	 * 
	 * 			- 회원 삭제 기능 컨트롤러 메서드 만들기(crudMemberDelete:post)   
	 * 			- 회원 삭제 기능 서비스 인터페이스 메서드 만들기                        
	 * 			- 회원 삭제 기능 서비스 클래스 메서드 만들기                        
	 * 			- 회원 삭제 기능 Mapper 인터페이스 메서드 만들기                  
	 * 			- 회원 삭제 기능 Mapper xml 쿼리 만들기                     
	 * 			- 회원 삭제 완료 페이지 만들기 (위에서 이미 만듬)                     
	 * 			- 확인
	 */	
	
	
	/*
	 * ============== 부스트랩을 이용한 CRUD를 진행해보자! ==============
	 * 
	 * ┌─────
	 * │ 페이지 모듈화를 위한 Tiles를 함께 사용하여 CRUD를 진행한다.
	 * └─────
	 * 
	 * 1. Tiles란?
	 * 		
	 * 		- 어떤 jsp를 템플릿으로 사용하고 템플릿의 각 영역을 어떤 내용으로 채울지에 대한 정보를 설정한다.
	 * 		- 하나의 화면들을 만들다보면 공통적이고 반복적으로 생성해야하는 header, footer와 같은 영역들이 존재한다.
	 * 		    우리는 그러한 공통 부분들을 분리하여 반복적으로 컴포넌트들을 사용하는 게 아니라 공통적인 부분은 한번만 가져다쓰고
	 * 		    변화하는 부분에 대해서만 동적으로 변환해 페이지를 관리할 수 있어야할 것입니다.
	 * 		    이렇게, header/footer/menu 등 공통적인 소스를 분리하여 한 화면에서 동적으로 레이아웃을 한 곳에 배치하여
	 * 	           설정하고 관리할 수 있도록 도와주는 페이지 모듈화를 돕는 프레임워크가 Tiles이다.
	 * 		
	 * 		** 모듈화를 진행할 페이지    
	 * 		- template.jsp (메인으로 사용하게 될 템플릿 페이지)
	 * 		- header.jsp (header 영역에서 사용할 페이지)
	 * 		- content source (body 영역에서 사용할 동적으로 변화될 페이지)
	 * 		- footer.jsp (footer 영역에서 사용할 페이지)
	 * 
	 * 			그 외에 다양한 영역의 페에지는 구현하고자 하는 시나리오를 바탕으로 페이지가 구성될 때 추가적으로
	 * 			레이아웃 영역을 분리하여 작성하면 된다.
	 * 
	 * 2. Tiles 설정
	 * 
	 * 		1) Tiles를 사용하기 위한 의존 관계 등록
	 * 			- tiles-core
	 * 			- tiles-api
	 * 			- tiles-servlet
	 * 			- tiles-jsp
	 * 
	 * 			** 의존  관계 등록 후 꼭 Project Update를 진행해서 라이브러리 등록을 진행해주세요!
	 * 		
	 * 		2) servlet-context.xml 수정
	 * 			- ViewResolver order 순서 변경
	 * 			- tilesViewResolver Bean 등록
	 * 
	 * 		3) tiles 설정을 위한 xml 생성
	 * 			- WEB-INF/spring/tiles-config.xml
	 * 
	 * 		3) tiles xml에 설정한 layout 설정대로 페이지를 생성 (.jsp)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
}





























































