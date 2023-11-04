package ru.magarusik.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.magarusik.microservice.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
}
