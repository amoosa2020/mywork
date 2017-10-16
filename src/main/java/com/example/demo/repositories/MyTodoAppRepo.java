package com.example.demo.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.MyTodo;


@Repository
public interface MyTodoAppRepo extends MongoRepository<MyTodo, String> {

}