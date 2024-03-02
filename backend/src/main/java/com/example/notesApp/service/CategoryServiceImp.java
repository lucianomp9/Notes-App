package com.example.notesApp.service;

import com.example.notesApp.Mapper.CategoryMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.notesApp.dto.CategoryDTO;
import com.example.notesApp.exceptions.ApiException;
import com.example.notesApp.exceptions.NotFoundExceptions.CategoryNotFoundException;
import com.example.notesApp.model.entity.Category;
import com.example.notesApp.repository.CategoryRepository;
import com.example.notesApp.service.interfaces.ICategoryService;
import java.util.List;

@Service
public class CategoryServiceImp implements ICategoryService {

    //Dependency Injection
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    public CategoryServiceImp(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    //POST
    @Transactional
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
       //Add check if exists by name.
        return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));
    }

    //GET
    @Transactional
    @Override
    public CategoryDTO getCategory(Long id) throws ApiException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Transactional
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.categoryListToCategoryDTOList(categories);
    }

    //PUT
    @Transactional
    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws ApiException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        //Checks if not null, then updates it.
        if(categoryDTO.getName() != null){
            category.setName(categoryDTO.name);
        }
        return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));
    }

    //DELETE
    @Transactional
    @Override
    public CategoryDTO deleteCategory(Long id) throws ApiException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.delete(category);
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public boolean categoryExists(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }


}
