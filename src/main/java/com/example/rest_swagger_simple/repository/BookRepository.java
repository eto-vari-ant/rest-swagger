package com.example.rest_swagger_simple.repository;

import com.example.rest_swagger_simple.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
