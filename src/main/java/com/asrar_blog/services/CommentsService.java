package com.asrar_blog.services;

import com.asrar_blog.entities.Comments;
import com.asrar_blog.payloads.CommentsDTO;

import java.util.List;

public interface CommentsService {
    CommentsDTO createComment(CommentsDTO commentsDTO,int userId, int postId);
    void deleteComment(int commentId);
    List<CommentsDTO> getAllCommentsForPost(int postId);
}
