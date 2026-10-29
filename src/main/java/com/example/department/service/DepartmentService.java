package com.example.department.service;

import com.example.department.exception.ResourceNotFoundException;
import com.example.department.model.Department;
import com.example.department.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department create(Department department) {
        if (repository.existsByNameIgnoreCase(department.getName().trim())) {
            throw new IllegalArgumentException("Department name already exists");
        }
        department.setName(department.getName().trim());
        return repository.save(department);
    }

    @Transactional(readOnly = true)
    public List<Department> findAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Department findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id));
    }

    public Department update(Long id, Department input) {
        Department existing = findById(id);
        String newName = input.getName().trim();
        if (!existing.getName().equalsIgnoreCase(newName) && repository.existsByNameIgnoreCase(newName)) {
            throw new IllegalArgumentException("Department name already exists");
        }
        existing.setName(newName);
        existing.setLocation(input.getLocation());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Department existing = findById(id);
        repository.delete(existing);
    }
}
