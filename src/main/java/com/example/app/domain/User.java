package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class User {
	
	private Integer id;
	@NotNull
	@Size(max = 30)
	private String loginId;
	@NotNull
	@Size(max = 60)
	private String loginPass;
	@NotNull
	@Size(max = 30)
	private String name;
	@NotNull
	@Size(max = 10)
	private String userType;
	
	private LocalDateTime registerAt;
	private LocalDateTime updateAt;
	
}
