package com.example.testbuoi7.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(length = 3, nullable = false)
    private String id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 3)
    private String sex;
    @Column(length = 200)
    private String hometown;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private Department department;
    @Column
    private int salary;
}
