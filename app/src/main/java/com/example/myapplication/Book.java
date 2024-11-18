package com.example.myapplication;

public class Book {
    private int id_book;
    private String name_book;
    private String Book_author;


    public Book(int id_book, String name_book, String Book_author) {
        this.Book_author = Book_author;
        this.id_book = id_book;
        this.name_book = name_book;
    }

    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book){
        this.id_book= id_book;
    }

    public String getBook_author() {
        return Book_author;
    }

    public void setBook_author(String book_author) {
       this.Book_author = book_author;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }
}



