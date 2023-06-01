package kr.or.ddit.controller.database;


public class DatabaseConnectController {
	/*
	 * 11장 데이터베이스 연동
	 * 
	 * 1. Oracle 11g 설치(클라이언트, 서버)
	 * 2. Oracle SQLDeveloper 설치
	 * 3. 데이터소스 설정
	 * 	- 애플리케이션이 데이터베이스에 접근하기 위한 추상화된 연결을 제공하는 역할을 한다.
	 * 
	 * 		스프링에서 설정할 수 있는 데이터 소스
	 * 		- JDBC 드라이버를 통해 선언된 데이터 소스
	 * 		- JNDI에 등록된 데이터 소스
	 * 		- 커넥션 풀링하는 데이터 소스
	 * 		- DBCP2에 등록된 데이터 소스
	 * 
	 * 		** JNDI란?
	 * 		- JNDI(Java Naming and Directory Interface)
	 * 		> 디렉터리 서비스에서 제공하는 데이터 및 객체를 발견(discover)하고 참고(lockup)하기 위한 자바 API이다.
	 * 
	 * 		1) 데이터베이스 JDBC 라이브러리 설정
	 * 		- pom.xml에서 의존성 추가
	 * 
	 * 		2) 데이터소스 설정
	 * 		- root-context.xml 설정
	 * 
	 * 4. CRUD 게시판
	 * - 데이터베이스 데이터에 접근하여 처리하는 방식(게시판 구성)
	 * 		> 스프링 JDBC
	 * 		> JPA
	 * 		> 마이바티스
	 * 		
	 * 		** 우리는 마이바티스를 이용한 CRUD 게시판을 진행하도록 한다.
	 * 
	 * 		1) Oracle Database에서 운용할 계정을 생성한다.
	 * 		2) 생성 SQL을 통해 관련 테이블을 생성한다.
	 * 		3) 게시판 작성을 위한 페이지(기본화면)
	 * 			- 등록화면
	 * 			- 등록 처리 후 화면
	 * 			- 목록화면
	 * 			- 상세보기 화면
	 * 			- 수정화면
	 * 			- 수정 처리 후 화면
	 * 			- 삭제 처리 후 화면
	 * 
	 * 5. 스프링 JDBC
	 * 	- SQL로만 데이터베이스를 쉽게 처리할 수 있도록 도와주는 JDBCTemplate 클래스를 제공한다.
	 * 
	 * 		1. JDBCTemplate 클래스가 제공하는 주요 메서드
	 * 
	 * 			queryForObject
	 * 			- 하나의 결과 레코드 중에서 하나의 컬럼 값을 가져온다.
	 * 			queryForMap
	 * 			- 하나의 결과 레코드 정보를 Map형태로 매핑할 수 있다.
	 * 			queryForList
	 * 			- 여러 개의 결과 레코드를 처리할 수 있다.
	 * 			query
	 * 			- ResultSetExtractor, RowCallbackHandler와 함께 조회할 때 사용한다.
	 * 			update
	 * 			- 데이터를 변경하는 SQL을 실행할 때 사용한다.
	 * 
	 * 		2. 스프링 JDBC 설정
	 * 
	 * 			spring-jdbc 의존관계 설정
	 * 			> pom.xml에 spring-jdbc 의존관계를 설정한다.
	 * 			데이터 소스 설정(root-context.xml)
	 * 			> jdbcTemplate 클래스를 빈으로 정의
	 * 
	 * 			<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
	 * 				<property name="dataSource" ref="dataSource">
	 * 			</bean>
	 * 
	 * 		3. 게시판 구현
	 * 
	 * 			** 기본적인 JDBC를 이용한 DB 설계는 DAO란에서 sql 쿼리가 노출되어 개발하는 방식이다보니 우리가 중급프로젝트 때 또는 JSP에서
	 * 			preparestatement 방식의 데이터베이스 연동과 다를바 없는 형태로, 게시판 구현을 진행하지 않고 내용만 간략히 보고 넘어간다.
	 * 			
	 * 6. JPA(Java Persistence API)
	 * - JPA는 자바 표준 ORM이다.
	 * 	즉, 구현되어 있는 클래스에 테이블을 매핑하기 위한 프레임워크이다.
	 * 
	 * 		# [장점]
	 * 		- SQL위주의 쿼리를 작성하지 않아도 된다.
	 * 		- 코드량이 엄청 줄어든다. (생산성)
	 * 		- 객체위주의 코드 작성으로 가독성이 좋아진다. (생산성)
	 * 		- 객체를 사용하는 시점에 쿼리를 전달하거나 동일한 트랜잭션 안에서 처리 내용의 같음을 보장한다.(패러다임의 불일치 해결)
	 * 
	 * 		# Entity
	 * 		- 데이터베이스에서 지속적으로 저장된 데이터를 자바 객체에 매핑한 것이다.
	 * 		- 메모리 상에 자바 객체의 인스턴스 형태로 존재하며 EntityManager에 의해 데이터베이스의 데이터와 동기화한다.
	 * 
	 * 		# EntityManager
	 * 		- 필요에 따라 Entity와 데이터베이스의 데이터를 동기화한다.
	 * 		- EntityManager에 제공하는 Entity 조작 API를 이용해 Entity에 대해 CRUD 작업을 할 수 있다.
	 * 
	 * 		# Entity 상태
	 * 		- new 상태 
	 * 		- 관리 상태
	 * 		- 분리 상태
	 * 		- 삭제된 상태
	 * 
	 * 		# JAP 관련 어노테이션
	 * 		자바빈즈 클래스 객체가 곧 테이블 구조와 같은 형태이기 때문에, VO클래스 내 @Entity어노테이션을 통해 해당 객체가 테이블과 같은 Entity임을
	 * 		설정하고 @Table어노테이션으로 테이블명을 설정해주면 VO클래스는 데이터베이스의 생성되어 있는 테이블과 연결되기 위한 준비가 된다.
	 * 		ex)
	 * 			@Entity
	 * 			@Table(name="board")
	 * 			public class Board{...}
	 * 		그리고 각 필드(멤버변수)에 @Column(name="board_no") 어노테이션으로 테이블 컬럼과 매핑 설정을 한다.
	 * 		설정된 Entity들을 이용하여 jap객체를 통해서 함수를 호출하면(persist, find 등등) 호출된 함수를 통해 Spring Data JPA가
	 * 		자동으로 Entity를 분석하여 함수에 따른 쿼리를 자동생성하여 결과를 실행, 조회, 수정 등을 처리해준다.
	 * 		기존 SQL 쿼리를 이용하여 서비스를 처리할 때에 필드가 추가되면 필드와 관련되어 있는 기능, sql, 추가된 필드 등을 수정해야하는 번거로움이
	 * 		발생하지만 JPA는 필드만 추가 후 테이블 정보와 매핑만 시켜주면 JPA가 자동으로 분석을 진행한다.
	 * 		그리고, 사용하는 데이터베이스 종류가 변경된다 하더라도 문제가 없다.(JPA는 SQL을 직접 건들이지 않고 함수와 같은 기능들로만 쿼리를 요청하고
	 * 		데이터베이스와 통신하기 때문이다.)
	 * 		
	 * 		JPA는 JPA구현체인 hibernate를 이용하여 사용한다. 그러기 위해선 관련 의존관계를 등록 후 사용해야한다.
	 * 		SQL문을 이용하여 테이버베이스를 연동하여 데이터를 가공하는 게 아닌, Method를 통해 데이터베이스를 조작할 수 있다는 장점이 있어 객체 모델을
	 * 		이용하여 비즈니스 로직을 구성하는데에만 집중을 할 수 있다. 하지만, 프로젝트의 규모와 복잡도가 클 때는 설계가 잘 못 되는 경우 해당 프로젝트의
	 * 		품질이 떨어진다는 문제가 있다. 그만큼 설계라인의 정확성이 필요하고 여러 방면에서 정확한 검증이 끝나지 않으면 적용하기가 까다롭다.
	 * 
	 * 	-----------------------------------------------------
	 * 	[용어]
	 * 	*** ORM이란?
	 * 		객체에 데이터를 읽고 쓰는 방법으로 관계형 데이터베이스에 데이터를 읽고 쓰는 기술이다.
	 * 	*** 기업 면접에서 ORM은 뭐 쓰셨어요? 라는 질문을 받는 경우가 있다.
	 * 		- 저는 ibatis를 사용했습니다, 저는 mybatis를 사용했습니다와 같은 대답을 하면 된다.
	 * 	-----------------------------------------------------
	 * 
	 * 7. 마이바티스
	 * - SQL과 자바 객체를 매핑하는 아이디어로 개발된 데이터베이스 접근용 프레임워크이다.
	 * 
	 * 	마이바티스 관련 내용은 12장에서 더 자세하게 알고 넘어간다.
	 * 
	 * 		1) 의존 관계 정의
	 * 		- pom.xml mybatis 설정을 위한 의존관계를 등록
	 * 		> mybatis
	 * 		> mybatis-spring
	 * 		> spring-jdbc
	 * 		> commons-dbcp2
	 * 		> log4jdbc-log4j2-jdbc4
	 * 		> ojdbc6 or ojdbc8
	 * 
	 *		2) 스프링과 마이바티스 연결 설정
	 *		- root-context.xml 설정
	 * 		> dataSource 
	 * 		> sqlSessionFactory
	 * 		> sqlSessionTemplate
	 * 
	 *		3) 마이바티스 설정
	 *		- webapp/WEB-INF/mybatisAlias/mybatisAlias.xml 설정
	 * 
	 *		4) 마이바티스 구현
	 *		- mapper 패키지 안에 Mapper 파일 구성(.xml)
	 *		- 예제로 mapper 패키지 안에 BoardMapper.xml을 구성하여 테스트를 진행한다.
	 */
}

































