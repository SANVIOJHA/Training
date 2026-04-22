package com.cap.booksecurity.service.impl;

import com.cap.booksecurity.dto.*;
import com.cap.booksecurity.entity.Book;
import com.cap.booksecurity.repository.BookRepository;
import com.cap.booksecurity.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    @Override
    public BookResponse add(BookRequest request) {
        Book book = new Book(null, request.getTitle(), request.getAuthor());
        Book saved = repo.save(book);

        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor());
    }

    @Override
    public List<BookResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(b -> new BookResponse(b.getId(), b.getTitle(), b.getAuthor()))
                .toList();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}