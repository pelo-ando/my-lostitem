package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.LostItem;

@Mapper
public interface LostItemMapper {

	List<LostItem> findAll() ; 
	LostItem findById(Integer id) ;
	void insert(LostItem lostItem) ;
//	List<LostItem> findByDate(Date from, Date to) throws Exception;
//	void update(LostItem lostItem) throws Exception;
//	void delete(Integer id) throws Exception;
//	long countAll() throws Exception;
//	long countFindByDate(Date from, Date to) throws Exception;
	
}
