package ru.magarusik.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.magarusik.microservice.entity.PostType;

import java.util.Optional;

@Repository
public interface PostTypeRepository extends JpaRepository<PostType, Long> {
    Optional<PostType> getPostTypeByNameIgnoreCase(String name);
}
