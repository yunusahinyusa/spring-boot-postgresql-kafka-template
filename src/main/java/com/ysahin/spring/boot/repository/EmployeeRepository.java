package com.ysahin.spring.boot.repository;

import com.ysahin.spring.boot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> { }
