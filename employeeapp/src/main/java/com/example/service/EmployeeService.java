package com.example.service;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeService{

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }

    public Employee save(Employee e){ return repo.save(e); }
    public List<Employee> getAll(){ return repo.findAll(); }
    public Employee getById(Long id){ return repo.findById(id).orElse(null); }
    public void delete(Long id){ repo.deleteById(id); }
}
