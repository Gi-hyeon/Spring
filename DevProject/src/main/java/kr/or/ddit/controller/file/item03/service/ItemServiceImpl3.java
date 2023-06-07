package kr.or.ddit.controller.file.item03.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.ItemMapper3;
import kr.or.ddit.vo.Item3;

@Service
public class ItemServiceImpl3 implements ItemService3 {

	@Inject
	private ItemMapper3 mapper;
	
	@Override
	public void register(Item3 item) {
		mapper.register(item);
		
		String[] files = item.getFiles();
		
		if (files == null) {
			return;
		}
		
		for(String fileName : files) {
			mapper.addAttach(fileName);
		}
	}

}



























