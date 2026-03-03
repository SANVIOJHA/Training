package com.example.springBasicsInitializer.repo;

import com.example.springBasicsInitializer.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Integer> {
}