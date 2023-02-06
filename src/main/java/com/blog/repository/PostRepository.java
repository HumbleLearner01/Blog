package com.blog.repository;

import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

//    List<Post> findByTitleContaining(String keyword);

    //does the same thing!
    @Query("select p from Post p where p.title like :keyword")
    List<Post> findByTitleContaining(@Param("keyword") String keyword);
}