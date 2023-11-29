package ru.magarusik.microservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
@Tag(
        name = "Контроллер постов",
        description = "Контроллер для работы с постами"
)
public class PostController {
    private final PostService postService;

    @GetMapping
    @Operation(summary = "Получить все посты", description = "Позволяет получить список всех постов")
    public @ResponseBody List<PostEntity> getAllPosts() {
        return postService
                .getAllPosts();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получить пост", description = "Получить пост по идентификатору")
    public @ResponseBody PostEntity getPostById(@PathVariable String id) {
        return postService
                .getPostById(Long.parseLong(id));
    }

    @PostMapping("/save")
    @Operation(summary = "Сохранить пост", description = "Позволяет сохранить пост")
    public void savePostEntity(@RequestBody PostEntity postEntity) {
        postService
                .savePost(postEntity);
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "Удалить пост", description = "Позволяет удалить пост по идентификатору")
    public void deletePostEntity(@PathVariable String id) {
        postService
                .deletePostById(Long.parseLong(id));
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Обновить пост", description = "Позволяет обновить пост под идентификатором")
    public void updatePostEntity(PostEntity postEntity) {
        postService
                .updatePostEntity(postEntity);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException exception) {
        return exception.getMessage();
    }
}
