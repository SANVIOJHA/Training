package com.cap.springDto.service;



import com.cap.springDto.dto.PageResponseData;
import com.cap.springDto.dto.StudentRequestDto;
import com.cap.springDto.dto.StudentResponseDto;

import java.util.List;

public interface IStudentService {

    StudentResponseDto saveStudent(StudentRequestDto requestDto);

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Integer id);

    void deleteStudentById(Integer id);

    PageResponseData<StudentResponseDto> getAllStudents(int page, int size);
}