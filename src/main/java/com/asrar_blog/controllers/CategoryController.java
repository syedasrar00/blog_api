package com.asrar_blog.controllers;

import com.asrar_blog.payloads.ApiResponse;
import com.asrar_blog.payloads.CategoryDTO;
import com.asrar_blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int id){
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.FOUND);
    }
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable int id, @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO,id),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
    }
}
