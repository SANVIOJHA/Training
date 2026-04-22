package com.cap.springDto.service;

import com.cap.springDto.StudentNotFoundException;
import com.cap.springDto.dto.PageResponseData;
import com.cap.springDto.dto.StudentRequestDto;
import com.cap.springDto.dto.StudentResponseDto;
import com.cap.springDto.entity.Student;
import com.cap.springDto.repo.StudentRepo;
import com.cap.springDto.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * StudentService
 *
 * Business logic layer.
 * Uses Mapper class for DTO <-> Entity conversion.
 *
 * Flow:
 * Controller -> Service -> Repository -> Database
 */

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepo studentRepo;

    // Save Student
    @Override
    public StudentResponseDto saveStudent(StudentRequestDto requestDto) {

        // Convert DTO to Entity
        Student student = Mapper.maptoEntity(requestDto);

        // Save to database
        Student savedStudent = studentRepo.save(student);

        // Convert Entity to Response DTO
        return Mapper.maptoDto(savedStudent);
    }

    // Get All Students
    @Override
    public List<StudentResponseDto> getAllStudents() {

        return studentRepo.findAll()
                .stream()
                .map(Mapper::maptoDto)
                .collect(Collectors.toList());
    }

    // Get Student By ID
    @Override
    public StudentResponseDto getStudentById(Integer id) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id)
                );

        return Mapper.maptoDto(student);
    }

    // Delete Student
    @Override
    public void deleteStudentById(Integer id) {

        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }

        studentRepo.deleteById(id);
    }



    public PageResponseData<StudentResponseDto> getAllStudents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepo.findAll(pageable);

        List<StudentResponseDto> dtoList = studentPage.getContent()
                .stream()
                .map(Mapper::maptoDto)
                .toList();

        return new PageResponseData<>(
                dtoList,
                studentPage.getNumber(),
                studentPage.getSize(),
                studentPage.getTotalElements(),
                studentPage.getTotalPages(),
                studentPage.isLast()
        );
    }

}