package ru.magarusik.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> getAllPosts() {
        return postRepository
                .findAll();
    }

    public PostEntity getPostById(Long id) {
        return postRepository
                .getReferenceById(id);
    }

    public void savePost(PostEntity postEntity) {
        postRepository.save(postEntity);
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public void updatePostEntity(PostEntity postEntity) {
        deletePostById(postEntity.id());
        savePost(postEntity);
    }

    public void deleteAllPosts() {
        postRepository.deleteAll();
    }
}
