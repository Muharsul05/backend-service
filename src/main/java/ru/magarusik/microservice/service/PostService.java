package ru.magarusik.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

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
        deletePostById(postEntity.getId());
        savePost(postEntity);
    }
}
