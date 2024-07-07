package com.inferno.crud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long rollNo;
	
	@Column
	private String task;
	
	@Column
	private boolean completed;
	
	

	public Todo() {
		super();
	}

	public Todo(long rollNo, String task, boolean completed) {
		super();
		this.rollNo = rollNo;
		this.task = task;
		this.completed = completed;
	}

	public long getRollNo() {
		return rollNo;
	}

	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	
}
