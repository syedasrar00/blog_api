package com.asrar_blog.services;

import com.asrar_blog.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryById(int id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, int id);
    void deleteCategory(int id);
}
