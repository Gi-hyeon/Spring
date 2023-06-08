package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.Item3;

public interface ItemMapper3 {

	public void register(Item3 item);

	public void addAttach(String fileName);

	public List<Item3> list();

	public Item3 read(int itemId);

	public List<String> getAttach(int itemId);

	public void modify(Item3 item);

	public void deleteAttach(int itemId);

	public void modifyAttach(@Param("fullName") String fileName, @Param("itemId") int itemId);

	public void delete(int itemId);

}
