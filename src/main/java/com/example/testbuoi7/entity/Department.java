package com.example.testbuoi7.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(length = 2, nullable = false)
    private String id;
    @Column(length = 30, nullable = false)
    private String name;
}