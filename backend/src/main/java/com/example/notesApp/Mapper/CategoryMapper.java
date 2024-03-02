package com.example.notesApp.Mapper;


import com.example.notesApp.dto.CategoryDTO;
import com.example.notesApp.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface CategoryMapper {
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
    CategoryDTO categoryToCategoryDTO(Category category);
    List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categories);
}
