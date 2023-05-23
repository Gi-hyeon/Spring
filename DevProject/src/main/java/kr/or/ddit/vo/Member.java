package kr.or.ddit.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	private String userId = "a001";
	private String userName = "hongkd";
	private String passWord = "1234";
	private int coin = 100;
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date dateOfBirth;
}
