package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemType {

	private int id;
	@NotNull
	@Max(20)
	private String name;
	private int regiPersonId;
	private LocalDateTime registerAt;
	
}
