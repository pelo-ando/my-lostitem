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
	
	@Override
	public List<LostItem> getLostItemList() {
		return lostItemMapper.findAll();
	}
	
	@Override
	public LostItem getLostItemById(Integer id) {
		return lostItemMapper.findById(id);
	}
	
	@Override
	public void addLostItem(LostItem lostItem) {
		lostItemMapper.insert(lostItem);
	}
	
	
}
