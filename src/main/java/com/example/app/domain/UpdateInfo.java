package com.example.app.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateInfo {

	private int id;
	@NotNull
	private int contentId;
	@Max(100)
	private String updateMemo;
	private int regiPersonId;
	private LocalDate updateAt;
	
}
