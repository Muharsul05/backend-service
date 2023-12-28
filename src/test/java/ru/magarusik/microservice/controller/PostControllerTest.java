package ru.magarusik.microservice.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.entity.PostType;
import ru.magarusik.microservice.repository.PostRepository;
import ru.magarusik.microservice.service.PostService;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
    public static final LocalDate CURRENT_DATE = LocalDate.parse(LocalDate.now().toString());
    public static final PostType TYPE = PostType.builder().id(1).name("type").build();
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;


    @Test
    void shouldReturnsAllPosts() {
        Mockito.when(postRepository.findAll()).thenReturn(getPosts());
        var result = postService.getAllPosts();
        Assertions.assertNotEquals(null, result);
        Assertions.assertFalse(result.isEmpty());
        System.out.println(result);
    }

    @Test
    void shouldReturnPostByID() {
        Mockito.when(postRepository.findAll()).thenReturn(getPosts());
        var result = postService.getPostById(1);
        System.out.println(result);
        Assertions.assertNotNull(result);
    }


    private List<PostEntity> getPosts() {
        return List
                .of(
                        PostEntity.builder()
                                .id(1)
                                .title("Title1")
                                .fullText("Full Text")
                                .date(CURRENT_DATE)
                                .type(TYPE)
                                .build(),
                        PostEntity.builder()
                                .id(2)
                                .title("Title2")
                                .fullText("Full Text")
                                .date(CURRENT_DATE)
                                .type(TYPE)
                                .build()
                );
    }
}