package com.example.springBasicsInitializer.controller;

import com.example.springBasicsInitializer.Student;
import com.example.springBasicsInitializer.service.IStudentService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final IStudentService iStudentService;

    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }
@PostMapping(path = "/saveStudent")
    public Student saveStudent(@RequestBody Student student){
       return iStudentService.save(student);


    }

}
