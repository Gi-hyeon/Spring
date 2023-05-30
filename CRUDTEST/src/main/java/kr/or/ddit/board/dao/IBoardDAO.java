package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardDAO {

	public MemberVO loginSuccess(MemberVO vo);

	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);

	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);

	public BoardVO selectBoardByboNo(int boNo);

	public void boHitIncrement(int boNo);

	public int boardInsert(BoardVO board);

	public int boardDelete(int boNo);

	public int boardUpdate(BoardVO board);

	public int userRegister(MemberVO member);

}
