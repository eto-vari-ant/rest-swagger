package com.example.rest_swagger_simple.service;

import com.example.rest_swagger_simple.model.Author;
import com.example.rest_swagger_simple.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }


    @Override
    public void delete(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
