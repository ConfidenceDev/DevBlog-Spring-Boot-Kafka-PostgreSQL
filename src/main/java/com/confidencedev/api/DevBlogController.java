package com.confidencedev.api;

import com.confidencedev.models.DevBlog;
import com.confidencedev.services.DevBlogServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController("api/v1/devblog")
public record DevBlogController (DevBlogServices devBlogServices){

    @PostMapping
    public String createPost(@RequestBody DevBlog devBlog){
        return devBlogServices.createAPost(devBlog);
    }

    @GetMapping
    public List<DevBlog> fetchAllPosts(){
        return devBlogServices.getAllPosts();
    }

    @GetMapping(path = "{id}")
    public DevBlog fetchPost(@PathVariable String title){
        return devBlogServices.getPost(title);
    }

    @PutMapping(path = "{id}")
    public String updatePost(@PathVariable Integer id, @RequestBody DevBlog devBlog){
        return devBlogServices.updateAPost(id, devBlog);
    }

    @DeleteMapping(path = "{id}")
    public void deletePost(@PathVariable Integer id){
        devBlogServices.deleteAPost(id);
    }
}
