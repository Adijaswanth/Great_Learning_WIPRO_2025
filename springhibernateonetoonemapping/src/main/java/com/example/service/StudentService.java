package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dao.IStudentDao;
import com.example.entity.Student;

@Service
public class StudentService {
    
    private final IStudentDao dao;

    public StudentService(IStudentDao dao) {
        this.dao = dao;
    }
    
    public void saveData(Student student) {
        dao.saveStudent(student);
    }
    
    public Student getStudent(Long id) {
        return dao.getStudent(id);
    }
}
