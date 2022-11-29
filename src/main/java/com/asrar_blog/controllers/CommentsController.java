package com.asrar_blog.controllers;

import com.asrar_blog.payloads.ApiResponse;
import com.asrar_blog.payloads.CommentsDTO;
import com.asrar_blog.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments/")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @PostMapping("/add/user/{userId}/post/{postId}")
    public ResponseEntity<CommentsDTO> addComment(@RequestBody CommentsDTO commentsDTO,
                                                  @PathVariable int userId,
                                                  @PathVariable int postId){
        return new ResponseEntity<>(commentsService.createComment(commentsDTO,userId,postId), HttpStatus.CREATED);
    }
    @GetMapping("/all/post/{postId}")
    public ResponseEntity<List<CommentsDTO>> getAllCommentsOfPost(@PathVariable int postId) {
        return new ResponseEntity<>(commentsService.getAllCommentsForPost(postId),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{commentId}/")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId){
        commentsService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted",true),HttpStatus.ACCEPTED);
    }
}
