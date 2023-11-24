package ru.magarusik.microservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @GetMapping
    public @ResponseBody List<PostEntity> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/get/{id}")
    public @ResponseBody PostEntity getPostById(@PathVariable String id) {
        return postService
                .getPostById(Long.parseLong(id));
    }

    @PostMapping("/save")
    public void savePostEntity(@RequestBody PostEntity postEntity) {
        postService
                .savePost(postEntity);
    }

    @PostMapping("/delete/{id}")
    public void deletePostEntity(@PathVariable String id) {
        postService
                .deletePostById(Long.parseLong(id));
    }

    @PostMapping("/update/{id}")
    public void updatePostEntity(PostEntity postEntity) {
        postService
                .updatePostEntity(postEntity);
    }
}
