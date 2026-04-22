package com.course.cmanagement.service;


import com.course.cmanagement.dto.CourseRequestDTO;
import com.course.cmanagement.dto.CourseResponseDTO;
import org.springframework.data.domain.Page;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO dto);
    CourseResponseDTO getCourseById(Long id);
    Page<CourseResponseDTO> getAllCourses(int page, int size, String sortBy);
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto);
    void deleteCourse(Long id);
}