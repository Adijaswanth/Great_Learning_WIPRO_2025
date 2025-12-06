package com.example.controller;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController{

    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping
    public Employee create(@RequestBody Employee e){
        return service.save(e);
    }

    @GetMapping
    public List<Employee> all(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee one(@PathVariable Long id){
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
