package com.example.notesApp.Mapper;

import com.example.notesApp.dto.Response.NoteResponseDTO;
import com.example.notesApp.model.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteResponseMapper {
    Note noteResponseDTOToNote(NoteResponseDTO noteResponseDTO);
    NoteResponseDTO noteToNoteResponseDTO(Note note);
    List<NoteResponseDTO> noteListToNoteResponseDTOList(List<Note> notes);

}
