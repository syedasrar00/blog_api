package com.asrar_blog.services.implementations;

import com.asrar_blog.entities.Comments;
import com.asrar_blog.entities.Post;
import com.asrar_blog.entities.User;
import com.asrar_blog.exceptions.ResourceNotFoundException;
import com.asrar_blog.payloads.CommentsDTO;
import com.asrar_blog.repositories.CommentsRepository;
import com.asrar_blog.services.CommentsService;
import com.asrar_blog.services.PostService;
import com.asrar_blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentsService {
    @Autowired
    private CommentsRepository repo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Override
    public CommentsDTO createComment(CommentsDTO commentsDTO, int userId, int postId) {
        User user = mapper.map(userService.getGetUserById(userId),User.class);
        Post post = mapper.map(postService.getPostById(postId), Post.class);
        Comments comments = mapper.map(commentsDTO,Comments.class);
        comments.setDate(new Date());
        comments.setUser(user);
        comments.setPost(post);
        return mapper.map(repo.save(comments),CommentsDTO.class);
    }

    @Override
    public void deleteComment(int commentId) {
        repo.delete(repo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments","commentId",""+commentId)));
    }
    @Override
    public List<CommentsDTO> getAllCommentsForPost(int postId) {
        Post post = mapper.map(postService.getPostById(postId), Post.class);
        List<Comments> commentsList=repo.findByPost(post);
        List<CommentsDTO> list = commentsList.stream().map(e->mapper.map(e,CommentsDTO.class)).collect(Collectors.toList());
        return list;
    }
}
