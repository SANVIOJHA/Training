package com.library.libmanagement.service;


import com.library.libmanagement.dto.BookDTO;
import com.library.libmanagement.entity.Book;

import java.util.List;

public interface BookService {

    Book addBook(BookDTO dto);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    void deleteBook(Long id);
}
