package com.example.notesApp.service.interfaces;

import com.example.notesApp.dto.CategoryDTO;
import com.example.notesApp.exceptions.ApiException;
import java.util.List;

public interface ICategoryService {

    //POST
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    //GET
    CategoryDTO getCategory(Long id) throws ApiException;

    List<CategoryDTO> getAllCategories();

    //PUT
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws ApiException;

    //DELETE
    CategoryDTO deleteCategory(Long id) throws ApiException;

    boolean categoryExists(Long categoryId);
}
