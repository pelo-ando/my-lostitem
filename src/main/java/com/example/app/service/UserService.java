package com.example.app.service;

import com.example.app.domain.User;

public interface UserService {
	
		User getUserByLoginId(String loginId) throws Exception;
}
