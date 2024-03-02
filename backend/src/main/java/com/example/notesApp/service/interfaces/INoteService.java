package com.example.notesApp.service.interfaces;

import com.example.notesApp.dto.NoteStatusUpdateRequest;
import com.example.notesApp.dto.Request.NoteRequestDTO;
import com.example.notesApp.dto.Response.NoteResponseDTO;
import com.example.notesApp.exceptions.ApiException;
import java.util.List;
import java.util.UUID;

public interface INoteService {

    //POST
    NoteResponseDTO createNote(NoteRequestDTO noteRequestDTO) throws ApiException;

    //GET
    NoteResponseDTO getNote(UUID uuid) throws ApiException;
    List<NoteResponseDTO> getAllNotes();

    //DELETE
    NoteResponseDTO deleteNote(UUID uuid) throws ApiException;

    //PUT
    NoteResponseDTO updateNote(UUID uuid, NoteRequestDTO noteRequestDTO) throws ApiException;
    NoteResponseDTO updateNoteStatusArchived(UUID uuid, NoteStatusUpdateRequest patchRequest) throws ApiException;
}
