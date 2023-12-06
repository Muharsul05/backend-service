package ru.magarusik.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.dto.PostEntityDto;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.entity.PostType;
import ru.magarusik.microservice.exception.PostNotFoundException;
import ru.magarusik.microservice.repository.PostRepository;
import ru.magarusik.microservice.utils.Converter;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostEntityDto> getAllPosts() {
        return postRepository
                .getAll()
                .stream()
                .map(Converter::postEntityToPostEntityDTO)
                .toList();
    }

    public PostEntityDto getPostById(long id) {
        var post = postRepository.getPostEntityById(id);
        if (post == null) {
            throw new PostNotFoundException("Post with id: " + id + " not found");
        }
        return Converter.postEntityToPostEntityDTO(post);
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

    public List<PostEntityDto> getPostEntityByType(PostType type) {
        return postRepository
                .getPostEntityByType(type)
                .stream()
                .map(Converter::postEntityToPostEntityDTO)
                .toList();
    }


}
