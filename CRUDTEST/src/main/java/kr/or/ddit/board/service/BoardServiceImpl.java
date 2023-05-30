package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {
	
	@Inject
	private IBoardDAO boardDao;

	@Override
	public MemberVO loginSuccess(MemberVO vo) {
		// TODO Auto-generated method stub
		return boardDao.loginSuccess(vo);
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO selectBoardByboNo(int boNo) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardByboNo(boNo);
	}

	@Override
	public void boHitIncrement(int boNo) {
		// TODO Auto-generated method stub
		boardDao.boHitIncrement(boNo);
	}

	@Override
	public ServiceResult boardInsert(BoardVO board) {
		// TODO Auto-generated method stub
		
		ServiceResult result = null;
		
		int status = boardDao.boardInsert(board);
		
		if (status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult boardDelete(int boNo) {
		// TODO Auto-generated method stub
		ServiceResult result = null;
		
		int status = boardDao.boardDelete(boNo);
		
		if (status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult boardUpdate(BoardVO board) {
		// TODO Auto-generated method stub
		
		ServiceResult result = null;
		
		int status = boardDao.boardUpdate(board);
		
		if (status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult userRegister(MemberVO member) {
		// TODO Auto-generated method stub
		ServiceResult result = null;
		
		int status = boardDao.userRegister(member);
		
		if (status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
}
