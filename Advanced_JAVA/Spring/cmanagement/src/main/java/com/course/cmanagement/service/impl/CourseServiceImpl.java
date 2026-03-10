package com.course.cmanagement.service.impl;



import com.course.cmanagement.dto.CourseRequestDTO;
import com.course.cmanagement.dto.CourseResponseDTO;
import com.course.cmanagement.entity.Course;
import com.course.cmanagement.exception.ResourceNotFoundException;
import com.course.cmanagement.repository.CourseRepository;
import com.course.cmanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final ModelMapper mapper;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = mapper.map(dto, Course.class);
        Course saved = repository.save(course);
        return mapper.map(saved, CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return mapper.map(course, CourseResponseDTO.class);
    }

    @Override
    public Page<CourseResponseDTO> getAllCourses(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Course> coursePage = repository.findAll(pageable);
        return coursePage.map(course -> mapper.map(course, CourseResponseDTO.class));
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto) {
        Course existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setInstructor(dto.getInstructor());
        existing.setDuration(dto.getDuration());
        existing.setPrice(dto.getPrice());
        return mapper.map(repository.save(existing), CourseResponseDTO.class);
    }

    @Override
    public void deleteCourse(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        repository.deleteById(id);
    }
}