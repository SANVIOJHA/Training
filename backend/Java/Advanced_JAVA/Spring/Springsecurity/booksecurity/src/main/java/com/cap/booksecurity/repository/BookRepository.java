package com.cap.booksecurity.repository;

import com.cap.booksecurity.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}