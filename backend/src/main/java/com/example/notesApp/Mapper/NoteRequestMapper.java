package com.example.notesApp.Mapper;

import com.example.notesApp.dto.Request.NoteRequestDTO;
import com.example.notesApp.model.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteRequestMapper {
    Note noteRequestDTOToNote(NoteRequestDTO noteRequestDTO);
    NoteRequestDTO noteToNoteRequestDTO(Note note);
    List<NoteRequestDTO> noteListToNoteRequestDTOList(List<Note> notes);
}
