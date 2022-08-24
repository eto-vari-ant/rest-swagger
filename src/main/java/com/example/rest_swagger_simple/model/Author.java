package com.example.rest_swagger_simple.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated author ID")
    private int id;

    @ApiModelProperty(notes = "The name of the author")
    private String name;

    @ApiModelProperty(notes = "The surname of the author")
    private String surname;

    @ApiModelProperty(notes = "The country where the author lives")
    private String country;

    @ApiModelProperty(notes = "The age of the author")
    private int age;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public void addBook(Book book){
        book.setAuthor(this);
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public Author() {
    }

    public Author(String name, String surname, String country, int age, List<Book> books) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.age = age;
        this.books = new ArrayList<>();
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return name+" "+surname;
    }
}
