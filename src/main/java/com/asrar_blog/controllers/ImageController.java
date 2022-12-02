package com.asrar_blog.controllers;

import com.asrar_blog.payloads.ImageDTO;
import com.asrar_blog.payloads.PostDTO;
import com.asrar_blog.services.FileService;
import com.asrar_blog.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/api/post/image/")
public class ImageController {
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    @Autowired
    private ImageService imageService;
    @PostMapping("/upload/{postId}")
    public ResponseEntity<ImageDTO> uploadPostImage(@PathVariable int postId, @RequestParam("image") MultipartFile image) throws IOException {
        String fileName = fileService.uploadImage(this.path,image);
        return ResponseEntity.ok(imageService.uploadImageForPost(fileName,postId));
    }
    @GetMapping(value="/{imageName}/", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
