package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.models.MyTodo;
import com.example.demo.repositories.MyTodoAppRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MyTodoAppController {
	
	@Autowired
	MyTodoAppRepo myTodoAppRepo;
	

	@Autowired
	MongoOperations mongoOperations;
	
	@GetMapping("/todos")
    public List<MyTodo> getAllTodos() {
		//
		//List<MyTodo> myTodoList = mongoOperations.find(query(where("Id").is(1)), MyTodo.class, "mytodoapp");
		//System.out.println("getAllTodos: " + myTodoList);
		
        return myTodoAppRepo.findAll();
    }
	
	@PostMapping("/createtodos")
    public MyTodo createTodo(@Valid @RequestBody MyTodo todo) {
		todo.setCompleted(false);
		todo.setStatus("Pending");
        return myTodoAppRepo.save(todo);
    }
	
	
    @DeleteMapping(value="/todos/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
    	myTodoAppRepo.delete(id);
    }

    
    @PutMapping(value="/todos/{id}")
    public ResponseEntity<MyTodo> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody MyTodo todo) {
        MyTodo todoData = myTodoAppRepo.findOne(id);
        if(todoData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
                
        todoData.setTitle(todo.getTitle());
        todoData.setCompleted(todo.getCompleted());
        
        if(todo.getCompleted())
        	todoData.setStatus("Completed");
        	
        MyTodo updatedTodo = myTodoAppRepo.save(todoData);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

}
