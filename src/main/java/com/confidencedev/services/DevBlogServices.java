package com.confidencedev.services;

import com.confidencedev.dao.DevBlogDao;
import com.confidencedev.models.DevBlog;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevBlogServices {

    private final DevBlogDao devBlogDao;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String createAPost(DevBlog devBlog){
        devBlog.setCreated(LocalDateTime.now());
        devBlogDao.save(devBlog);
        kafkaTemplate.send("msg", "Post Created successfully!");
        return "Post Created successfully!";
    }

    public List<DevBlog> getAllPosts(){
        return devBlogDao.findAll();
    }

    public DevBlog getPost(String title){
        Optional<DevBlog> post = devBlogDao.findByTitle(title);
        return post.orElse(null);
    }

    public String updateAPost(Integer id, DevBlog devBlog){
        Optional<DevBlog> post = devBlogDao.findById(id);
        if (post.isEmpty()) return "No post found with id";

        post.get().setCreator(devBlog.getCreator());
        post.get().setTitle(devBlog.getTitle());
        post.get().setDescription(devBlog.getDescription());
        post.get().setCreated(LocalDateTime.now());

        devBlogDao.save(post.get());
        kafkaTemplate.send("msg", "Post Updated successfully!");
        return "Post updated!";
    }

    public void deleteAPost(Integer id){
        kafkaTemplate.send("msg", "Post Deleted successfully!");
        devBlogDao.deleteById(id);
    }
}
