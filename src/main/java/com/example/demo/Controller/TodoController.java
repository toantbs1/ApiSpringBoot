package com.example.demo.Controller;

import com.example.demo.Model.Todo;
import com.example.demo.Service.TodoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TodoController {
    @Autowired
    private TodoService todoService;

    private List<Todo> todos;
    @PostConstruct
    public void init() {
        todoService.add(null);
    }
    @GetMapping("/listTodo")
    public ResponseEntity<List<Todo>> getAll(@RequestParam(value = "limit", required = false) Integer limit) {
        todos = todoService.findAll(limit);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<Todo> get(@PathVariable(name = "todoId") Long todoId, Model model) {
        Todo todo1 =  todoService.getProductById(todoId);
        return  ResponseEntity.ok(todo1);
    }

    @PostMapping("/addTodo")
    public ResponseEntity<Void> addTodo(@RequestBody Todo todo) {
        todoService.add(todo);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/updateTodo/{todoId}")
    public ResponseEntity<Void> updateTodo(@PathVariable(name = "todoId")Long todoId, @RequestBody Todo todo) {
        todoService.update(todoId, todo);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteTodo/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable(name = "todoId")Long todoId) {
        todoService.deleteTodo(todoId);
        return  ResponseEntity.ok().build();
    }
}
