package com.example.notesApp.controller;


import com.example.notesApp.dto.CategoryDTO;
import com.example.notesApp.exceptions.ApiException;
import com.example.notesApp.service.CategoryServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("http://localhost:5173")
public class CategoryController {

    //Dependency Injection
    private final CategoryServiceImp categoryServiceImp;
    public CategoryController(CategoryServiceImp categoryServiceImp){
        this.categoryServiceImp = categoryServiceImp;
    }

    //POST
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) throws ApiException {
        return new ResponseEntity<>(categoryServiceImp.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    //GET
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) throws ApiException {
        return new ResponseEntity<>(categoryServiceImp.getCategory(id), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(categoryServiceImp.getAllCategories(), HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) throws ApiException {
        return new ResponseEntity<>(categoryServiceImp.deleteCategory(id), HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws ApiException {
        return new ResponseEntity<>(categoryServiceImp.updateCategory(id, categoryDTO), HttpStatus.OK);
    }

}
