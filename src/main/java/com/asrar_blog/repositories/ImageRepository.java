package com.asrar_blog.repositories;

import com.asrar_blog.entities.Image;
import com.asrar_blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> findByPost(Post post);
}
