package com.example.rest_swagger_simple.service;

import com.example.rest_swagger_simple.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> getAuthorById(int id);
    void save (Author author);
    void delete(int id);
    List<Author> getAllAuthors();
}
