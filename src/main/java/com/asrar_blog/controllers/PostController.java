package com.asrar_blog.controllers;

import com.asrar_blog.payloads.ApiResponse;
import com.asrar_blog.payloads.PostDTO;
import com.asrar_blog.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNumber,pageSize),HttpStatus.OK);
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
    public ResponseEntity<PostResponse> getPostByUserId(@PathVariable int userId,
                                                        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(postService.getAllPostsByUser(userId, pageNumber, pageSize),HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategoryId(@PathVariable int categoryId,
                                                            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(postService.getAllPostsByCategory(categoryId, pageNumber, pageSize),HttpStatus.OK);
    }
}
