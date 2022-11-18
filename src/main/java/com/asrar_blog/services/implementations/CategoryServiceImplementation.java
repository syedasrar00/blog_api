package com.asrar_blog.services.implementations;

import com.asrar_blog.entities.Category;
import com.asrar_blog.exceptions.ResourceNotFoundException;
import com.asrar_blog.payloads.CategoryDTO;
import com.asrar_blog.repositories.CategoryRepository;
import com.asrar_blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImplementation implements CategoryService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepository repo;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        Category savedCategory = repo.save(category);
        return mapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return repo.findAll().stream().map(element -> mapper.map(element, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        return mapper.map(repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryID",id)), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) {
        Category category = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryID",id));
        categoryDTO.setCategoryId(category.getCategoryId());
        category = mapper.map(categoryDTO , Category.class);
        return mapper.map(repo.save(category),CategoryDTO.class);
    }

    @Override
    public void deleteCategory(int id) {
        Category category = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryID",id));
        repo.delete(category);
    }
}
