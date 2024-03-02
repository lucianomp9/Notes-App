package com.example.notesApp.controller;

import com.example.notesApp.dto.NoteStatusUpdateRequest;
import com.example.notesApp.dto.Request.NoteRequestDTO;
import com.example.notesApp.dto.Response.NoteResponseDTO;
import com.example.notesApp.exceptions.ApiException;
import com.example.notesApp.service.NoteServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("http://localhost:5173")
public class NoteController {

    //Dependency injection
    private final NoteServiceImp noteServiceImp;
    public NoteController(NoteServiceImp noteServiceImp){
        this.noteServiceImp = noteServiceImp;
    }

    //POST
    @PostMapping("/note")
    public ResponseEntity<?> createNote(@RequestBody NoteRequestDTO noteRequestDTO) throws ApiException {
        return new ResponseEntity<>(noteServiceImp.createNote(noteRequestDTO), HttpStatus.CREATED);
    }

    //GET
    @GetMapping("/note/{id}")
    public ResponseEntity<NoteResponseDTO> getNote(@PathVariable UUID id) throws ApiException {
        return new ResponseEntity<>(noteServiceImp.getNote(id), HttpStatus.OK);
    }

    @GetMapping("/note")
    public ResponseEntity<List<NoteResponseDTO>> getAllNotes(){
        return new ResponseEntity<>(noteServiceImp.getAllNotes(), HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("/note/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable UUID id) throws ApiException {
        return new ResponseEntity<>(noteServiceImp.deleteNote(id), HttpStatus.OK);
    }

    //PUT
    @PutMapping("/note/{id}")
    public ResponseEntity<?> updateNote(@PathVariable UUID id, @RequestBody NoteRequestDTO noteRequestDTO) throws ApiException {
        return new ResponseEntity<>(noteServiceImp.updateNote(id, noteRequestDTO), HttpStatus.OK);
    }

    @PatchMapping("/note/{id}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable UUID id, @RequestBody NoteStatusUpdateRequest patchRequest) throws ApiException {
        return new ResponseEntity<>(noteServiceImp.updateNoteStatusArchived(id, patchRequest), HttpStatus.OK);
    }


}
