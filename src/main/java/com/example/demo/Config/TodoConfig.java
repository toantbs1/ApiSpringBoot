package com.example.demo.Config;

import com.example.demo.Model.TodoValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoConfig {
    @Bean
    public TodoValidator validator() {
        return new TodoValidator();
    }
}
