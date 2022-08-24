package com.example.rest_swagger_simple.controller;

import com.example.rest_swagger_simple.model.Author;
import com.example.rest_swagger_simple.model.Book;
import com.example.rest_swagger_simple.service.AuthorServiceImpl;
import com.example.rest_swagger_simple.service.BookServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/authors/")
@Api(value="", tags={"Main Controller"})
@Tag(name="Main Controller", description = "Controller that helps to manage authors and books")
public class AuthorRestController {

    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private BookServiceImpl bookService;


    @ApiOperation(value="Get the author by id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Id is 0"),
            @ApiResponse(code = 200, message = "This author exists"),
            @ApiResponse(code = 404, message = "This author doesnt exist"),
            }
    )
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Integer id){
        if(id==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Author> author = authorService.getAuthorById(id);
        if(author.isPresent()){
            return new ResponseEntity<>(author.get(), HttpStatus.OK);
        } else return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Create new author", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Author is null"),
            @ApiResponse(code = 201, message = "The author was created"),
    }
    )
    @RequestMapping(value="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> createAuthor(@RequestBody @Validated Author author){
        if(author==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        authorService.save(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }


    @ApiOperation(value="Delete the author by id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Id is 0"),
            @ApiResponse(code = 204, message = "This author was deleted"),
            @ApiResponse(code = 404, message = "This author doesnt exist"),
    }
    )
    @RequestMapping(value="{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") Integer id){
        if(id==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Author> author = authorService.getAuthorById(id);
        if(author.isPresent()){
            authorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Get all the authors", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Here you are"),
            @ApiResponse(code = 404, message = "There arent any authors"),
    }
    )
    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> authors = authorService.getAllAuthors();
        if(authors.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @ApiOperation(value="Get all the books of author with id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Id is 0"),
            @ApiResponse(code = 204, message = "This author doesnt have any book"),
            @ApiResponse(code = 404, message = "This author doesnt exist"),
    }
    )
    @RequestMapping(value = "books/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBooksByAuthorById(@PathVariable("id") Integer id){
        if(id==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Author> author = authorService.getAuthorById(id);
        if(author.isPresent()){
            List<Book> books = bookService.getAllBooksByAuthor(author.get());
            if(books.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else return new ResponseEntity<>(bookService.getAllBooksByAuthor(author.get()), HttpStatus.OK);
        } else return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Create the book of the author with id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Id is 0"),
            @ApiResponse(code = 201, message = "The book was created"),
            @ApiResponse(code = 404, message = "This author doesnt exist"),
    }
    )
    @RequestMapping(value="book/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBookByAuthorId(@PathVariable("id") Integer id, @RequestBody @Validated Book book){
        if(id==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Author> author = authorService.getAuthorById(id);
        if(author.isPresent()){
            bookService.createBookByAuthor(author.get(), book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}
