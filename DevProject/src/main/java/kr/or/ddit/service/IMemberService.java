package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {

	public void register(MemberVO member);

	public List<MemberVO> list();

	public MemberVO read(int userNo);

	public void modify(MemberVO member);

	public void remove(int userNo);
	
}
