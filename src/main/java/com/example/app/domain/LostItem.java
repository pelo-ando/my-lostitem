package com.example.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LostItem {

	
	private int id;
//	@NotBlank
	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate findDate;
	@NotNull
	@Size(max = 30)
	private String content;
	@NotNull
	@Size(max =10)
	private String findPersonName;
	private String status;
	private String memo;
	private LocalDateTime registerAt;
	private LocalDateTime updateAt;
	
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

