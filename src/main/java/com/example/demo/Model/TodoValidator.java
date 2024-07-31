package com.example.demo.Model;

import com.example.demo.Model.Todo;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

public class TodoValidator {
    public boolean isValid(Todo todo) {
        return Optional.ofNullable(todo)
                .filter(todo1 -> !StringUtils.isEmpty(todo1.getTitle()))
                .filter(todo1 -> !StringUtils.isEmpty(todo1.getDetail()))
                .isPresent();
    }
}
