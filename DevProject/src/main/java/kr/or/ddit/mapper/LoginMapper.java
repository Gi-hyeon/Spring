package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface LoginMapper {

	public DDITMemberVO idCheck(String memId);

	public int singup(DDITMemberVO memberVO);

	public DDITMemberVO loginCheck(DDITMemberVO memberVO);

	public String idForgetProcess(DDITMemberVO member);

	public String pwForgetProcess(DDITMemberVO member);

	public DDITMemberVO readByUserId(String username);

	public void signupAuth(int memNo);

}
