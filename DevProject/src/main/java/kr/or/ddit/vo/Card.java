package kr.or.ddit.vo;

import java.util.Date;

import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Card {
	@NotBlank
	private String no;
	// 미래 날짜인지 검사
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validMonth;
}
