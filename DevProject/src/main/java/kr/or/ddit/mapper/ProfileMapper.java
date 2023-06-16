package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface ProfileMapper {

	public DDITMemberVO selectMember(String memId);

	public int profileUpdate(DDITMemberVO memberVO);

}
