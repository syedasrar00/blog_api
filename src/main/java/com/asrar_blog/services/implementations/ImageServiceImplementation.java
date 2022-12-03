package com.asrar_blog.services.implementations;

import com.asrar_blog.entities.Image;
import com.asrar_blog.entities.Post;
import com.asrar_blog.exceptions.ResourceNotFoundException;
import com.asrar_blog.payloads.ImageDTO;
import com.asrar_blog.repositories.ImageRepository;
import com.asrar_blog.repositories.PostsRepository;
import com.asrar_blog.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImplementation implements ImageService {
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private PostsRepository postRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ImageDTO uploadImageForPost(String imageName, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",""+postId));
        Image image = new Image();
        image.setPost(post);
        image.setImageName(imageName);
        post.getPostImageList().add(image);
        postRepo.save(post);
        return mapper.map(imageRepo.save(image),ImageDTO.class);
    }
}
