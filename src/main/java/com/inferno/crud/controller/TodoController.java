package com.inferno.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inferno.crud.controller.dtos.CrudDto;
import com.inferno.crud.entities.Todo;
import com.inferno.crud.repositories.TodoRepo;

import jakarta.validation.Valid;


@RestController
public class TodoController {
	
	@Autowired
	private TodoRepo todoRepo;
	
	@GetMapping("/todos")
	public List<Todo> todos(@RequestParam Optional<Boolean> completed) {
		return completed.map(c -> this.todoRepo.findAllByCompleted(c))
				.orElseGet(() -> this.todoRepo.findAll());
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
		return this.todoRepo.findById(id)
				.map(todo -> ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/todos")
	public ResponseEntity<Object> addTodo(@Valid @RequestBody CrudDto crudDto) {
		this.todoRepo.save(new Todo(0, crudDto.getTask(), false));
		return ResponseEntity.ok().build();
				
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<Object> updateTodo(@PathVariable Long id, @Valid @RequestBody CrudDto crudDto) {
		return this.todoRepo.findById(id)
				.map(todo -> {
					todo.setTask(crudDto.getTask());
					this.todoRepo.save(todo);
					return ResponseEntity.ok().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/todos/{id}/mark-complete")
	public ResponseEntity<Object> markComplete(@PathVariable Long id) {
		return this.todoRepo.findById(id)
				.map(todo -> {
					todo.setCompleted(true);
					this.todoRepo.save(todo);
					return ResponseEntity.ok().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/todos/{id}/mark-incomplete")
	public ResponseEntity<Object> markInComplete(@PathVariable Long id) {
		return this.todoRepo.findById(id)
				.map(todo -> {
					todo.setCompleted(false);
					this.todoRepo.save(todo);
					return ResponseEntity.ok().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Object> deleteTodo(@PathVariable Long id) {
		return this.todoRepo.findById(id)
				.map(todo -> {
					this.todoRepo.delete(todo);
					return ResponseEntity.ok().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}
