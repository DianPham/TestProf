package com.example.testbuoi7.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column
    private String username;
    @Column
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> roles = new HashSet<>();
}