package com.example.app.service;

import java.util.List;

import com.example.app.domain.LostItem;

public interface LostItemService {

	List<LostItem> getLostItemList();		
	LostItem getLostItemById(Integer id);
	void addLostItem(LostItem lostItem);
	// pagination導入で書き直し
//	List<LostItem> getLostItemByDate(Date from, Date to) throws Exception;
//	void deleteLostItem(Integer id) throws Exception;
//	void editLostItem(LostItem lostItem) throws Exception;
//	List<LostItem> getLostItemListPerPage(int page, int numPerPage) throws Exception;
//	int TotalPages(int numPage) throws Exception;
	
	
}
