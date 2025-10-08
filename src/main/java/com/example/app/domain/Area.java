package com.example.app.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Area {

	private int id;
	private String name;
	private int regiPersonId;
	private LocalDate registerAt;
	
}
