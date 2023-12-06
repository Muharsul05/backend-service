package ru.magarusik.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.entity.PostType;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT * FROM post_entity", nativeQuery = true)
    List<PostEntity> getAll();

    PostEntity getPostEntityById(long id);

    List<PostEntity> getPostEntityByType(PostType type);
}
