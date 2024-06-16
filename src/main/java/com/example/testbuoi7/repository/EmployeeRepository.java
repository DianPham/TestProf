package com.example.testbuoi7.repository;

import com.example.testbuoi7.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Page<Employee> findAll(Pageable pageable);
}