package com.asrar_blog.payloads;

import javax.validation.constraints.*;

public class CategoryDTO {
    private int categoryId;
    @NotEmpty
    @Size(min=4, message="CategoryName must be at least 4 characters")
    private String categoryName;

    public CategoryDTO() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
