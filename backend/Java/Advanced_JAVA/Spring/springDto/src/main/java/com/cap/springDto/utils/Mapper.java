package com.cap.springDto.utils;

import com.cap.springDto.dto.StudentRequestDto;
import com.cap.springDto.dto.StudentResponseDto;
import com.cap.springDto.entity.Student;

/*
 * Mapper Utility Class
 *
 * Responsible for converting:
 * 1. StudentRequestDto -> Student (Entity)
 * 2. Student -> StudentResponseDto
 *
 * This keeps mapping logic separate from service layer.
 */



//Client → Controller → Service → Mapper → Repository → Database


public class Mapper {

    // Convert Request DTO to Entity
    public static Student maptoEntity(StudentRequestDto dto) {

        if (dto == null) {
            return null;
        }

        return Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .course(dto.getCourse())
                .build();
    }

    // Convert Entity to Response DTO
    public static StudentResponseDto maptoDto(Student student) {

        if (student == null) {
            return null;
        }

        return StudentResponseDto.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .course(student.getCourse())
                .build();
    }
}