package com.asrar_blog.controllers;

import com.asrar_blog.entities.Post;
import com.asrar_blog.exceptions.ResourceNotFoundException;
import com.asrar_blog.payloads.ApiResponse;
import com.asrar_blog.payloads.PostDTO;
import com.asrar_blog.payloads.PostResponse;
import com.asrar_blog.repositories.PostsRepository;
import com.asrar_blog.services.FileService;
import com.asrar_blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostController {
    @Autowired
    private FileService fileService;
    @Autowired
    private PostService postService;
    @Value("${project.image}")
    private String path;
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
    @GetMapping("/title/{postTitle}")
    public ResponseEntity<PostResponse> getPostTitle(@PathVariable String postTitle,
                                                        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(postService.getAllPostsByTitle(postTitle, pageNumber, pageSize),HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategoryId(@PathVariable int categoryId,
                                                            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(postService.getAllPostsByCategory(categoryId, pageNumber, pageSize),HttpStatus.OK);
    }
    @GetMapping("/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(@PathVariable int postId, @RequestParam("image") MultipartFile image) throws IOException {
        String fileName = fileService.uploadImage(this.path,image);
        PostDTO post = postService.getPostById(postId);
        post.setPostImageURI(fileName);
        return ResponseEntity.ok(postService.updatePost(post, postId));
    }
}
