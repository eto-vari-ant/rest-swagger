package com.example.rest_swagger_simple.repository;

import com.example.rest_swagger_simple.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
