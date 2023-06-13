package kr.or.ddit.service;

import java.io.IOException;
import java.util.List;

import kr.or.ddit.vo.Board;

public interface IBoardService {

	public void register(Board board) throws IOException;

	public List<Board> list();

	public Board read(int boardNo);

	public void update(Board board);

	public void delete(int boardNo);

	public List<Board> search(Board board);
	
}
