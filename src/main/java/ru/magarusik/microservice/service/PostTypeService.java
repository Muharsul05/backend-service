package ru.magarusik.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.entity.PostType;
import ru.magarusik.microservice.repository.PostTypeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostTypeService {
    private final PostTypeRepository postTypeRepository;

    public List<PostType> getAllPostTypes() {
        return postTypeRepository.findAll();
    }

    public PostType getPostTypeByName(String name) {
        return postTypeRepository.getPostTypeByNameIgnoreCase(name);
    }

}
