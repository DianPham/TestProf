package com.example.testbuoi7.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;
    @Column
    private String name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRole> roles = new HashSet<>();
}