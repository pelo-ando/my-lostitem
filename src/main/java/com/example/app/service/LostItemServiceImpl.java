package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.LostItem;
import com.example.app.mapper.LostItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LostItemServiceImpl implements LostItemService {

	private final LostItemMapper lostItemMapper;
//	@Autowired
//	LostItemMapper lostItemMapper;
	
	@Override
	public List<LostItem> getLostItemList() {
		return lostItemMapper.findAll();
	}
	
	@Override
	public LostItem getLostItemById(Integer id) {
		return lostItemMapper.findById(id);
	}
	
	// ページ分割機能用
	@Override
	public List<LostItem> getLostItemsByPage(int page, int numPerPage) {
		int offset = numPerPage * (page - 1);
		return lostItemMapper.findLimited(offset, numPerPage);
	}
	
	@Override
	public int getTotalPages(int numPerPage) {
		double totalNum = (double) lostItemMapper.count();
		return (int) Math.ceil(totalNum / numPerPage);
	}
	
	@Override
	public void addLostItem(LostItem lostItem) {
		lostItemMapper.insert(lostItem);
	}
	
	
}
