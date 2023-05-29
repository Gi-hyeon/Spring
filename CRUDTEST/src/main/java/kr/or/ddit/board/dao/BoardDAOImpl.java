package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class BoardDAOImpl implements IBoardDAO {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVO loginSuccess(MemberVO vo) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("Board.loginSuccess", vo);
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("Board.selectBoardCount", pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Board.selectBoardList", pagingVO);
	}

	@Override
	public BoardVO selectBoardByboNo(int boNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("Board.selectBoardByboNo", boNo);
	}

	@Override
	public void boHitIncrement(int boNo) {
		// TODO Auto-generated method stub
		sqlSession.update("Board.boHitIncrement", boNo);
	}

	@Override
	public int boardInsert(BoardVO board) {
		// TODO Auto-generated method stub
		return sqlSession.insert("Board.boardInsert", board);
	}

	@Override
	public int boardDelete(int boNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("Board.boardDelete", boNo);
	}

	@Override
	public int boardUpdate(BoardVO board) {
		// TODO Auto-generated method stub
		return sqlSession.update("Board.boardUpdate", board);
	}

	@Override
	public int userRegister(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.insert("Board.userRegister", member);
	}
	
}

