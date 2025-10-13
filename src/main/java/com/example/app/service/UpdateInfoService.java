package com.example.app.service;

import java.util.List;

import com.example.app.domain.UpdateInfo;

public interface UpdateInfoService {

	List<UpdateInfo> getUpdateInfoList() throws Exception;
	List<UpdateInfo> getUpdateInfoById(Integer id) throws Exception;
	
}
