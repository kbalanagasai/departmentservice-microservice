package com.example.department.controller;

import com.example.department.model.Department;
import com.example.department.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department create(@Valid @RequestBody Department department) {
        return service.create(department);
    }

    @GetMapping
    public List<Department> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @Valid @RequestBody Department department) {
        return service.update(id, department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
