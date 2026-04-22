package com.course.cmanagement.controller;



import com.course.cmanagement.dto.*;
import com.course.cmanagement.payload.ApiResponse;
import com.course.cmanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponseDTO>> createCourse(
            @Valid @RequestBody CourseRequestDTO dto) {
        CourseResponseDTO created = service.createCourse(dto);
        return new ResponseEntity<>(
                new ApiResponse<>(true, "Course created successfully", created),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ApiResponse<CourseResponseDTO> getCourse(@PathVariable Long id) {
        return new ApiResponse<>(true, "Course fetched successfully", service.getCourseById(id));
    }

    @GetMapping
    public ApiResponse<Page<CourseResponseDTO>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return new ApiResponse<>(true, "Courses fetched successfully",
                service.getAllCourses(page, size, sortBy));
    }

    @PutMapping("/{id}")
    public ApiResponse<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO dto) {
        return new ApiResponse<>(true, "Course updated successfully", service.updateCourse(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return new ApiResponse<>(true, "Course deleted successfully", null);
    }
}