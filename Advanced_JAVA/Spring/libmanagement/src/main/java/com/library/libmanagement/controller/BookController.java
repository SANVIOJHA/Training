package com.library.libmanagement.controller;

import com.library.libmanagement.dto.BookDTO;
import com.library.libmanagement.entity.Book;
import com.library.libmanagement.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // root endpoint
    @GetMapping("/")
    public String home() {
        return "hello learning testing";
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody BookDTO dto) {
        return bookService.addBook(dto);
    }

    @GetMapping("/books/test")
    public String hello(){
        return "hello testing";
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }
}