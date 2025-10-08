package com.example.app.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Strage {

	private int id;
	private String name;
	private LocalDate registerAt;
	private LocalDate updateAt;
}
