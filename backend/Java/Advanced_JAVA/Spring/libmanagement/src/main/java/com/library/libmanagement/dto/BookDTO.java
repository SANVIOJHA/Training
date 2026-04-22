package com.library.libmanagement.dto;


import lombok.Data;

@Data
public class BookDTO {

    private String title;
    private String author;
    private String isbn;
    private Long categoryId;
}