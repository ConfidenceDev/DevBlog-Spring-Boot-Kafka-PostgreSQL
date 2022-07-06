package com.confidencedev.dao;

import com.confidencedev.models.DevBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevBlogDao
        extends JpaRepository<DevBlog, Integer> {

    Optional<DevBlog> findByTitle(String title);
}