package com.example.springBasicsInitializer.service;

import com.example.springBasicsInitializer.Student;
import com.example.springBasicsInitializer.repo.IStudentRepo;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService{
    private final IStudentRepo iStudentRepo;

    public StudentService(IStudentRepo iStudentRepo) {
        this.iStudentRepo = iStudentRepo;
    }

    @Override
    public Student save(Student student) {
        System.out.println(student.getId());
        System.out.println(student.getName());
        return iStudentRepo.save(student);
    }
}
