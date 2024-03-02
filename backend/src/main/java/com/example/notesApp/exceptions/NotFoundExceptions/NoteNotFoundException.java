package com.example.notesApp.exceptions.NotFoundExceptions;

import com.example.notesApp.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class NoteNotFoundException extends ApiException {
    public NoteNotFoundException(UUID id){
        super("No note was found with id " + id, HttpStatus.NOT_FOUND);
    }
}
