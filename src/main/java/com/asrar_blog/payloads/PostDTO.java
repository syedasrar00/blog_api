package com.asrar_blog.payloads;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class PostDTO {
    private int postId;
    @NotEmpty
    @Size(min=4, message="Title must be at least 4 characters")
    private String PostTitle;
    @NotEmpty
    @Size(min=4, message="Content must be at least 4 characters")
    private String postContent;
    @NotEmpty(message="Cannot be empty")
    private String postImageURI;
    private Date publishDate;
    private CategoryDTO category;
    private UserDTO user;
    public PostDTO() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImageURI() {
        return postImageURI;
    }

    public void setPostImageURI(String postImageURI) {
        this.postImageURI = postImageURI;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
