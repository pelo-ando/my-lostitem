package com.example.app.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LostItem {

	private int id;
	private LocalDate findDate;
	@NotNull
//	@Max(30)
	private String content;
	@NotNull
//	@Max(10)
	private String findPersonName;
	private String status;
	private String memo;
	private LocalDate registerAt;
	private LocalDate updateAt;
	
	// 忘れ物分類	
	private int typeId;
	private String typeName;
	
	//	拾得場所
	private int  areaId;
	private String areaName;
	
	//	保管場所
	private int strageId;			// 場所
	private String strageName;
	
	//登録者
//	private int userId;
//	private String userName;
}

