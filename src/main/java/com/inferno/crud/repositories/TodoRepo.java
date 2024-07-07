package com.inferno.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inferno.crud.entities.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long>{
	
	public List<Todo> findAllByCompleted(Boolean completed);
}
