package com.example.rest_swagger_simple.service;

import com.example.rest_swagger_simple.model.Author;
import com.example.rest_swagger_simple.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooksByAuthor(Author author);
    void createBookByAuthor(Author author, Book book);
}
