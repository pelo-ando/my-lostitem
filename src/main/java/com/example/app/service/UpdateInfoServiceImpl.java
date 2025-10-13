package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.UpdateInfo;
import com.example.app.mapper.UpdateInfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
	public class UpdateInfoServiceImpl implements UpdateInfoService{
	
		 private final UpdateInfoMapper updateInfoMapper;
	
		@Override
		public List<UpdateInfo> getUpdateInfoList() throws Exception {
			return updateInfoMapper.selectAll();
		}
		 
		@Override
		public List<UpdateInfo> getUpdateInfoById(Integer id) throws Exception {
		return updateInfoMapper.selectById(id);
		}
}