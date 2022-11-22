package com.asrar_blog.services;


import com.asrar_blog.payloads.PostDTO;
import com.asrar_blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse getAllPosts(int pageNumber, int pageSize);
    PostDTO getPostById(int postId);
    PostDTO createPost(PostDTO postDto, int userId, int categoryId);
    PostDTO updatePost(PostDTO postDto, int postId);
    void deletePost(int postId);
    PostResponse getAllPostsByCategory(int categoryId, int pageNumber, int pageSize);
    PostResponse getAllPostsByUser(int userId, int pageNumber, int pageSize);
}
