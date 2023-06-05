package kr.or.ddit.controller.file.item01.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.ItemMapper;
import kr.or.ddit.vo.Item;

@Service
public class ItemServiceImpl implements ItemService {

	@Inject
	private ItemMapper mapper;
	
	@Override
	public void register(Item item) {
		mapper.create(item);
	}

}
