package com.inferno.crud.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CrudDto {
	
	@NotBlank
	private String task;

	public CrudDto() {
		super();
	}

	public CrudDto(@NotBlank String task) {
		super();
		this.task = task;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	
}
