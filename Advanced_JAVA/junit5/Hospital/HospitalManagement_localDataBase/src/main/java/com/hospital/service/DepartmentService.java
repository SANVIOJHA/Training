package com.hospital.service;

import com.hospital.entity.Department;
import jakarta.persistence.*;

import java.util.List;

public class DepartmentService {

    private EntityManager em;

    public DepartmentService(EntityManager em) {
        this.em = em;
    }

    public void saveDepartment(Department department) {
        em.persist(department);
    }

    public Department getDepartment(Long id) {
        return em.find(Department.class, id);
    }

    public List<Department> getAllDepartments() {
        return em.createQuery("FROM Department", Department.class)
                .getResultList();
    }

    public void updateDepartment(Department department) {
        em.merge(department);
    }

    public void deleteDepartment(Long id) {
        Department department = em.find(Department.class, id);
        if (department != null) {
            em.remove(department);
        }
    }
}