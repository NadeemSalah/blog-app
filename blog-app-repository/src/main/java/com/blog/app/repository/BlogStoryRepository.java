package com.blog.app.repository;

import com.blog.app.repository.model.BlogStory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogStoryRepository extends JpaRepository<BlogStory, Long> {

    List<BlogStory> findAllByOrderByCreateDateTimeDesc();
}
