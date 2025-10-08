package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.User;

@Mapper
public interface UserMapper {

	List<User> findAll() throws Exception;
	User findById(Integer id) throws Exception;
	void insert(User user) throws Exception;
	void update(User user) throws Exception;
	void delete(Integer id) throws Exception;
	long countAll() throws Exception;
	
}
