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
    List<PostDTO> getAllPostsByCategory(int categoryId);
    List<PostDTO> getAllPostsByUser(int userId);
}
