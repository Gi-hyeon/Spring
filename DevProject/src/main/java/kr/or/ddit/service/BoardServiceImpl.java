package kr.or.ddit.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BoardMapper;
import kr.or.ddit.vo.Board;

@Service
public class BoardServiceImpl implements IBoardService {
	
	@Inject
	private BoardMapper mapper;
	
	@Override
	public void register(Board board) {
		mapper.create(board);
	}

}
