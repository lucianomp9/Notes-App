package com.example.notesApp.dto.Request;

import com.example.notesApp.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteRequestDTO {
    public String title;
    public String description;
    public CategoryDTO category;
    public boolean status;
    public boolean archived;
}
