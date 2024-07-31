package com.example.demo.Service;

import com.example.demo.Model.Todo;
import com.example.demo.Model.TodoValidator;
import com.example.demo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoValidator todoValidator;

    public List<Todo> findAll(Integer limit) {
        return Optional.ofNullable(limit)
                .map(value -> todoRepository.findAll(PageRequest.of(0, value)).getContent())
                .orElseGet(() -> todoRepository.findAll());
    }
    public Todo getProductById(Long todoId) {
        return todoRepository.findById(todoId).get();
    }
    public Todo add(Todo todo) {
        if(todoValidator.isValid(todo)) {
            return todoRepository.save(todo);
        }
        return null;
    }
    public Todo update(Long todoId, Todo todo) {
        return todoRepository.findById(todoId)
                .map(todo1 -> {
                    todo1.setTitle(todo.getTitle());
                    todo1.setDetail(todo.getDetail());
                    return todoRepository.save(todo1);
                }).get();
    }
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
