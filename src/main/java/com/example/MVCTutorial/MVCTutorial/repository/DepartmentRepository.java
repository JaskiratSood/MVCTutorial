package com.example.MVCTutorial.MVCTutorial.repository;

import com.example.MVCTutorial.MVCTutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Double> {
}
