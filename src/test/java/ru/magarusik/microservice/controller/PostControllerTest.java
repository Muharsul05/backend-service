package ru.magarusik.microservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.repository.PostRepository;
import ru.magarusik.microservice.service.PostService;

import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void shouldReturnsAllPosts() {
        Mockito.when(postRepository.findAll()).thenReturn(getPosts());
        System.out.println(postService.getAllPosts());
    }

    private List<PostEntity> getPosts() {
        return List
                .of(
                        PostEntity.builder()
                                .id(1)
                                .title("Title1")
                                .fullText("Full Text")
                                .date(new Date())
                                .type("type")
                                .build(),
                        PostEntity.builder()
                                .id(2)
                                .title("Title2")
                                .fullText("Full Text")
                                .date(new Date())
                                .type("type")
                                .build()
                );
    }


}