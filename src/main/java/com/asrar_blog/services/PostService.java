package com.asrar_blog.services;


import com.asrar_blog.payloads.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
    PostDTO getPostById(int postId);
    PostDTO createPost(PostDTO postDto, int userId, int categoryId);
    PostDTO updatePost(PostDTO postDto, int postId);
    void deletePost(int postId);
    List<PostDTO> getAllPostsByCategory(int categoryId);
    List<PostDTO> getAllPostsByUser(int userId);
}
