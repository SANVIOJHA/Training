package com.course.cmanagement.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Instructor name is required")
    private String instructor;

    @Min(value = 1, message = "Duration must be at least 1 hour")
    private int duration;

    @PositiveOrZero(message = "Price cannot be negative")
    private Double price;
}