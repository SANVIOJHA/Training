package com.library.libmanagement.service.impl;


import com.library.libmanagement.dto.BookDTO;
import com.library.libmanagement.entity.Book;
import com.library.libmanagement.entity.Category;
import com.library.libmanagement.repository.BookRepository;
import com.library.libmanagement.repository.CategoryRepository;
import com.library.libmanagement.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Book addBook(BookDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .category(category)
                .build();

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}