package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardService {

	public MemberVO loginSuccess(MemberVO vo);

	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);

	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);

	public BoardVO selectBoardByboNo(int boNo);

	public void boHitIncrement(int boNo);

	public ServiceResult boardInsert(BoardVO board);

	public ServiceResult boardDelete(int boNo);

	public ServiceResult boardUpdate(BoardVO board);

	public ServiceResult userRegister(MemberVO member);

}
