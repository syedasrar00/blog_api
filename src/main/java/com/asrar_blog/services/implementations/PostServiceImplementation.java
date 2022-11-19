package com.asrar_blog.services.implementations;

import com.asrar_blog.entities.Category;
import com.asrar_blog.entities.Post;
import com.asrar_blog.entities.User;
import com.asrar_blog.exceptions.ResourceNotFoundException;
import com.asrar_blog.payloads.PostDTO;
import com.asrar_blog.repositories.CategoryRepository;
import com.asrar_blog.repositories.PostsRepository;
import com.asrar_blog.repositories.UserRepo;
import com.asrar_blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostsRepository postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepo.findAll().stream().map((e)->mapper.map(e,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(int postId) {
        return mapper.map(postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId)),PostDTO.class);
    }

    @Override
    public PostDTO createPost(PostDTO postDto, int userId, int categoryId) {
        Post post = mapper.map(postDto,Post.class);
        post.setPublishDate(new Date());
        post.setUser(userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId)));
        post.setCategory(categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId)));
        return mapper.map(postRepo.save(post),PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        Post updatedPost = mapper.map(postDto,Post.class);
        updatedPost.setPostId(post.getPostId());
        updatedPost.setUser(post.getUser());
        updatedPost.setCategory(post.getCategory());
        updatedPost.setPublishDate(post.getPublishDate());
        return mapper.map(postRepo.save(updatedPost),PostDTO.class);
    }

    @Override
    public void deletePost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDTO> getAllPostsByCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryID",categoryId));
        return postRepo.findByCategory(category).stream().map((e)->mapper.map(e,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAllPostsByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
        return postRepo.findByUser(user).stream().map((e)->mapper.map(e,PostDTO.class)).collect(Collectors.toList());

    }
}
