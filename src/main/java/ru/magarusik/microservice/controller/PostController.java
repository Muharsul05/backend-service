package ru.magarusik.microservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.magarusik.microservice.dto.PostEntityDTO;
import ru.magarusik.microservice.service.PostService;
import ru.magarusik.microservice.service.PostTypeService;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@Log
@Tag(
        name = "Контроллер постов",
        description = "Контроллер для работы с постами"
)
public class PostController {
    private final PostService postService;
    private final PostTypeService postTypeService;

    @GetMapping
    @Operation(summary = "Получить все посты", description = "Позволяет получить список всех постов")
    public List<PostEntityDTO> getAllPosts() {
        return postService
                .getAllPosts();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получить пост", description = "Получить пост по идентификатору")
    public PostEntityDTO getPostById(@PathVariable String id) {
        return postService
                .getPostById(Long.parseLong(id));
    }

    @PostMapping("/save")
    @Operation(summary = "Сохранить пост", description = "Позволяет сохранить пост")
    public void savePostEntity(PostEntityDTO postEntityDTO) {
        log.log(Level.INFO, String.valueOf(postEntityDTO));
        postService
                .savePost(postEntityDTO);
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "Удалить пост", description = "Позволяет удалить пост по идентификатору")
    public void deletePostEntity(@PathVariable String id) {
        postService
                .deletePostById(Long.parseLong(id));
    }

    @PostMapping("/update")
    @Operation(summary = "Обновить пост", description = "Позволяет обновить пост под идентификатором")
    public void updatePostEntity(PostEntityDTO postEntityDTO) {
        postService
                .updatePostEntity(postEntityDTO);
    }

    @GetMapping("/type/{name}")
    public List<PostEntityDTO> getPostsByTypeName(@PathVariable String name) {
        return postTypeService
                .getPostsByType(postTypeService.getPostTypeByName(name));
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException exception) {
        return exception.getMessage();
    }
}
