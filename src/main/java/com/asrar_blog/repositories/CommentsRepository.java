package com.asrar_blog.repositories;

import com.asrar_blog.entities.Comments;
import com.asrar_blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
    List<Comments> findByPost(Post post);
}
