package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateInfo {

	private int id;
	@Max(100)
	private String memo;
	private LocalDateTime updateAt;
	
	// 忘れ物ID
	@NotNull
	private int contentId;
	private String contentName;
	
	// UserID	
	private int modifyPersonId;
	private String modifyPersonName;


}
