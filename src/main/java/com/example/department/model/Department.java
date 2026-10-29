package com.example.department.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "departments", uniqueConstraints = @UniqueConstraint(name = "uk_department_name", columnNames = "name"))
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name must be at most 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Size(max = 150, message = "Location must be at most 150 characters")
    @Column(length = 150)
    private String location;

    public Department() {}

    public Department(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
