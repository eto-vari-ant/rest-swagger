package com.example.rest_swagger_simple.service;

import com.example.rest_swagger_simple.model.Author;
import com.example.rest_swagger_simple.model.Book;
import com.example.rest_swagger_simple.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooksByAuthor(Author author) {
        return author.getBooks();
    }

    @Override
    public void createBookByAuthor(Author author, Book book) {
        author.addBook(book);
        bookRepository.save(book);
    }
}
