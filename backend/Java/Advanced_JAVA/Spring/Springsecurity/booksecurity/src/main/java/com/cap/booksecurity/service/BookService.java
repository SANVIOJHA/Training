package com.cap.booksecurity.service;

import com.cap.booksecurity.dto.*;
import java.util.List;

public interface BookService {

    BookResponse add(BookRequest request);

    List<BookResponse> getAll();

    void delete(Long id);
}