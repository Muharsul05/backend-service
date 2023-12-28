package ru.magarusik.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.magarusik.microservice.dto.PostEntityDTO;
import ru.magarusik.microservice.exception.PostNotFoundException;
import ru.magarusik.microservice.repository.PostRepository;
import ru.magarusik.microservice.utils.Converter;

import java.util.List;

import static ru.magarusik.microservice.utils.Converter.postEntityDTOToPostEntity;
import static ru.magarusik.microservice.utils.Converter.postEntityToPostEntityDTO;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostEntityDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(Converter::postEntityToPostEntityDTO)
                .toList();
    }

    public PostEntityDTO getPostById(long id) {
        var post = postRepository.getPostEntityById(id);
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post with id: " + id + " not found");
        }
        return postEntityToPostEntityDTO(post.get());
    }

    public void savePost(@RequestBody PostEntityDTO postEntityDTO) {
        var post = postEntityDTOToPostEntity(postEntityDTO);
        postRepository.save(post);
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public void updatePostEntity(PostEntityDTO postEntityDTO) {
        savePost(postEntityDTO);
    }
}
