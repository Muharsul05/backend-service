package ru.magarusik.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.dto.PostEntityDTO;
import ru.magarusik.microservice.entity.PostType;
import ru.magarusik.microservice.repository.PostTypeRepository;
import ru.magarusik.microservice.utils.Converter;

import java.util.List;

@Service
@AllArgsConstructor
public class PostTypeService {
    private final PostTypeRepository postTypeRepository;


    public PostType getPostTypeByName(String name) {
        var postType = postTypeRepository.getPostTypeByNameIgnoreCase(name);
        if (postType.isEmpty()) {
            throw new IllegalArgumentException(String.format("Post type with name: %s not found", name));
        }
        return postType.get();
    }

    public List<PostEntityDTO> getPostsByType(PostType type) {
        return type.getPosts().stream()
                .map(Converter::postEntityToPostEntityDTO)
                .toList();
    }
}
