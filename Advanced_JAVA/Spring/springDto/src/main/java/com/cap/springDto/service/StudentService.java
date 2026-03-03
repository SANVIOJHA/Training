package com.cap.springDto.service;

import com.cap.springDto.StudentNotFoundException;
import com.cap.springDto.dto.StudentRequestDto;
import com.cap.springDto.dto.StudentResponseDto;
import com.cap.springDto.entity.Student;
import com.cap.springDto.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Controller → IStudentService → StudentService → StudentRepo → Database


@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepo studentRepo;

    //  SAVE STUDENT
    @Override
    public StudentResponseDto saveStudent(StudentRequestDto requestDto) {

        Student student = Student.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .course(requestDto.getCourse())
                .build();

        Student savedStudent = studentRepo.save(student);

        return StudentResponseDto.builder()
                .id(savedStudent.getId())
                .name(savedStudent.getName())
                .email(savedStudent.getEmail())
                .course(savedStudent.getCourse())
                .build();
    }

    // GET ALL STUDENTS
    @Override
    public List<StudentResponseDto> getAllStudents() {

        return studentRepo.findAll()
                .stream()
                .map(student -> StudentResponseDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .email(student.getEmail())
                        .course(student.getCourse())
                        .build())
                .collect(Collectors.toList());
    }

    //  GET STUDENT BY ID (Using if-else  )
    @Override
    public StudentResponseDto getStudentById(Integer id) {

        Optional<Student> optionalStudent = studentRepo.findById(id);

        if (optionalStudent.isPresent()) {

            Student student = optionalStudent.get();

            return StudentResponseDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .course(student.getCourse())
                    .build();

        } else {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
    }

    @Override
    public void deleteStudentById(Integer id) {

        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }

        studentRepo.deleteById(id);
    }
}