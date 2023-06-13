package kr.or.ddit.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.BoardMapper;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements IBoardService {
	
	@Inject
	private BoardMapper mapper;
	
	@Transactional(rollbackFor = IOException.class)
	@Override
	public void register(Board board) throws IOException {
		log.info("Board register");
		mapper.create(board);
		
		// CheckedException 계열로 롤백 처리가 되지 않는다. -> 롤백 처리하게 가능
//						> (rollbackFor = IOException.class)
		if (true) {
			throw new IOException();
		}
		
		// RuntimeException 계열로 롤백 처리가 가능하다.
//		if (true) {
//			throw new NullPointerException();
//		}
	}

	@Override
	public List<Board> list() {
		log.info("Board list");
		return mapper.list();
	}

	@Override
	public Board read(int boardNo) {
		log.info("Board read");
		return mapper.read(boardNo);
	}

	@Override
	public void update(Board board) {
		log.info("Board update");
		mapper.update(board);
	}

	@Override
	public void delete(int boardNo) {
		log.info("Board delete");
		mapper.delete(boardNo);
	}

	@Override
	public List<Board> search(Board board) {
		log.info("Board search");
		return mapper.search(board);
	}

}
