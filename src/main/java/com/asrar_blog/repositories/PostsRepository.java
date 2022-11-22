package com.asrar_blog.repositories;

import com.asrar_blog.entities.Category;
import com.asrar_blog.entities.Post;
import com.asrar_blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostsRepository extends JpaRepository<Post,Integer> {
    Page<Post> findByCategory(Category category, Pageable page);
    Page<Post> findByUser(User user, Pageable page);
    Page<Post> findByPostTitle(String postTitle, Pageable page);
}
