package ru.magarusik.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.magarusik.microservice.entity.PostType;

@Repository
public interface PostTypeRepository extends JpaRepository<PostType, Long> {
    PostType getPostTypeByNameIgnoreCase(String name);
}
