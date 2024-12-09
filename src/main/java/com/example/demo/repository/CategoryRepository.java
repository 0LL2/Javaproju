package com.example.demo.repository;

import com.example.demo.model.Category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Logger LOGGER = LoggerFactory.getLogger(CategoryRepository.class);
    List<Category> findByNameContainingIgnoreCase(String name);


    default boolean existsByNameDebug(String name) {
        LOGGER.debug("Checking existence of category with name: {}", name);
        boolean exists = existsByName(name);
        LOGGER.debug("Existence check result for name '{}': {}", name, exists);
        return exists;
    }

    boolean existsByName(String name);
}
