package ru.magarusik.microservice.service;

import org.springframework.stereotype.Service;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> getAllPosts() {
        return postRepository
                .findAll();
    }

    public PostEntity getPostById(Long id) {
        var post = postRepository.getPostEntityById(id);
        if (post == null) {
            throw new RuntimeException("Post with id: " + id + " not found");
        }
        return post;
    }

    public void savePost(PostEntity postEntity) {
        postRepository.save(postEntity);
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public void updatePostEntity(PostEntity postEntity) {
        deletePostById(postEntity.getId());
        savePost(postEntity);
    }
}
