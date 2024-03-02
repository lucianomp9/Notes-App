package com.example.notesApp.exceptions.NotFoundExceptions;

import com.example.notesApp.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiException {
    public CategoryNotFoundException(Long id) {
        super("No category was found with id " + id, HttpStatus.NOT_FOUND);
    }
}
