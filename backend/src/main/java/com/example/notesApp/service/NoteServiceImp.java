package com.example.notesApp.service;

import com.example.notesApp.dto.CategoryDTO;
import com.example.notesApp.dto.NoteStatusUpdateRequest;
import org.springframework.stereotype.Service;
import com.example.notesApp.dto.Request.NoteRequestDTO;
import com.example.notesApp.dto.Response.NoteResponseDTO;
import com.example.notesApp.exceptions.ApiException;
import com.example.notesApp.exceptions.NotFoundExceptions.NoteNotFoundException;
import com.example.notesApp.Mapper.CategoryMapper;
import com.example.notesApp.Mapper.NoteRequestMapper;
import com.example.notesApp.Mapper.NoteResponseMapper;
import com.example.notesApp.model.entity.Category;
import com.example.notesApp.model.entity.Note;
import com.example.notesApp.repository.NoteRepository;
import com.example.notesApp.service.interfaces.INoteService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImp implements INoteService {

    //Dependency injection
     private final NoteRepository noteRepository;
     NoteRequestMapper noteRequestMapper;
     NoteResponseMapper noteResponseMapper;
     CategoryMapper categoryMapper;
     CategoryServiceImp categoryServiceImp;

    public NoteServiceImp(NoteRepository noteRepository, NoteResponseMapper noteResponseMapper, NoteRequestMapper noteRequestMapper, CategoryMapper categoryMapper,
                          CategoryServiceImp categoryServiceImp){
        this.noteRepository = noteRepository;
        this.noteRequestMapper = noteRequestMapper;
        this.noteResponseMapper = noteResponseMapper;
        this.categoryMapper = categoryMapper;
        this.categoryServiceImp = categoryServiceImp;
    }


    //POST
    @Override
    @Transactional
    public NoteResponseDTO createNote(NoteRequestDTO noteRequestDTO) throws ApiException {
        Category category = new Category();
        Note note = new Note();
        Long categoryId = noteRequestDTO.getCategory().getId();
        if (categoryId != null &&  categoryServiceImp.categoryExists(categoryId)) {
            category = categoryMapper.categoryDTOToCategory(categoryServiceImp.getCategory(categoryId));
        } else {
            category = categoryMapper.categoryDTOToCategory(categoryServiceImp.createCategory(noteRequestDTO.getCategory()));
        }


        note.setUuid(UUID.randomUUID());
        note.setTitle(noteRequestDTO.getTitle());
        note.setCategory(category);
        note.setDescription(noteRequestDTO.getDescription());
        note.setStatus(false);
        note.setArchived(false);

        return noteResponseMapper.noteToNoteResponseDTO(noteRepository.save(note));
    }

    //GET
    @Override
    @Transactional
    public NoteResponseDTO getNote(UUID uuid)throws ApiException {
        Note note = noteRepository.findById(uuid).orElseThrow(() -> new NoteNotFoundException(uuid));
        return noteResponseMapper.noteToNoteResponseDTO(note);
    }

    @Transactional
    @Override
    public List<NoteResponseDTO> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return noteResponseMapper.noteListToNoteResponseDTOList(notes);
    }

    //DELETE
    @Transactional
    @Override
    public NoteResponseDTO deleteNote(UUID uuid) throws ApiException{
        Note note = noteRepository.findById(uuid).orElseThrow(() -> new NoteNotFoundException(uuid));
        noteRepository.delete(note);
        return noteResponseMapper.noteToNoteResponseDTO(note);
    }


    //PUT
    @Transactional
    @Override
    public NoteResponseDTO updateNote(UUID uuid, NoteRequestDTO noteRequestDTO) throws ApiException {
        Note note = noteRepository.findById(uuid).orElseThrow(() -> new NoteNotFoundException(uuid));

        if (noteRequestDTO.getTitle() != null){
            note.setTitle(noteRequestDTO.getTitle());
        }

        if(noteRequestDTO.getDescription() != null){
            note.setDescription(noteRequestDTO.getDescription());
        }
        //Checks if category can be edited
        if(noteRequestDTO.getCategory() != null){
            //If Category id was sent, updates it by searching it on repository
            if(noteRequestDTO.getCategory().getId() != null){
                CategoryDTO categoryDTO = categoryServiceImp.getCategory(noteRequestDTO.getCategory().getId());
                note.setCategory(categoryMapper.categoryDTOToCategory(categoryDTO));
            }else if(noteRequestDTO.getCategory().getName() != null){ //If it doesn't exists, creates a new category.
                note.setCategory(categoryMapper.categoryDTOToCategory(categoryServiceImp.createCategory(noteRequestDTO.getCategory())));
            }
        }

        return noteResponseMapper.noteToNoteResponseDTO(noteRepository.save(note));
    }

    @Override
    public NoteResponseDTO updateNoteStatusArchived(UUID uuid, NoteStatusUpdateRequest patchRequest) throws ApiException {
        Note note = noteRepository.findById(uuid).orElseThrow(() -> new NoteNotFoundException(uuid));

        if (patchRequest.getStatus() != null) {
            note.setStatus(patchRequest.getStatus());
        }

        if (patchRequest.getArchived() != null) {
            note.setArchived(patchRequest.getArchived());
        }

        return noteResponseMapper.noteToNoteResponseDTO(noteRepository.save(note));
    }


}
