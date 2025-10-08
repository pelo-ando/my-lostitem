package com.example.app.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class User {
	
	private Integer id;
	@NotNull
	@Max(30)
	private String loginId;
	@NotNull
	@Max(60)
	private String loginPass;
	@NotNull
	@Max(30)
	private String name;
	
	private String userType;
	
	private LocalDate registerAt;
	private LocalDate updateAt;
	
}
