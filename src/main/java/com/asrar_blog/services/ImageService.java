package com.asrar_blog.services;

import com.asrar_blog.payloads.ImageDTO;

import java.util.List;

public interface ImageService {
//    List<ImageDTO> getAllImagesForPost(int postId);
    ImageDTO uploadImageForPost(String imageName,int postId);
}
