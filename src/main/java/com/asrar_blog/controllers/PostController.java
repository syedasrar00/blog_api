package com.asrar_blog.controllers;

import com.asrar_blog.payloads.ApiResponse;
import com.asrar_blog.payloads.PostDTO;
import com.asrar_blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO post, @PathVariable int userId, @PathVariable int categoryId){
        return new ResponseEntity<>(postService.createPost(post,userId,categoryId), HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int postId, @RequestBody PostDTO post){
        return new ResponseEntity<>(postService.updatePost(post,postId),HttpStatus.CREATED);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Deleted Sucessfully",true),HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable int userId){
        return new ResponseEntity<>(postService.getAllPostsByUser(userId),HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategoryId(@PathVariable int categoryId){
        return new ResponseEntity<>(postService.getAllPostsByCategory(categoryId),HttpStatus.OK);
    }
}
