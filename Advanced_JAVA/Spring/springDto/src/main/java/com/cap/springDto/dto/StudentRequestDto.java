package com.cap.springDto.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

//
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class StudentRequestDto {
    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Course is mandatory")
    private String course;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
}