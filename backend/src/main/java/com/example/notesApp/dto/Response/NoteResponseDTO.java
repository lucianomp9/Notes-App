package com.example.notesApp.dto.Response;

import com.example.notesApp.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteResponseDTO {

    public UUID uuid; //Generates random UUID in response
    public String title;
    public String description;
    public CategoryDTO category;
    public boolean status;
    public boolean archived;
}
