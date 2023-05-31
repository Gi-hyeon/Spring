package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	@NotBlank(message = "야 ID가 비었잖아 ㅡㅡ")
	//@Size(min = 5, max = 20, message = "5~20글자 사이로 입력하쇼")
	private String userId = "a001";
	@NotBlank(message = "야 이름이 비었잖아 ㅡㅡ")
	@Size(max = 3)
	private String userName = "hongkd";
	@NotBlank(message = "야 비밀번호가 비었잖아 ㅡㅡ")
	private String password = "1234";
	private int coin = 100;
	//@DateTimeFormat(pattern = "yyyyMMdd")
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@Email
	private String email;
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private List<String> hobbyList;
	private boolean foreigner;
	private String developer;
	private String nationality;
	// 중첩된 자바빈즈의 입력값 검증을 지정
	@Valid
	private Address address;
	// 중첩된 자바빈즈의 입력값 검증을 지정
	@Valid
	private List<Card> cardList;
	private String cars;
	private String[] carArray;
	private List<String> carList;
	private String introduction;
	private String birthDay;
}
