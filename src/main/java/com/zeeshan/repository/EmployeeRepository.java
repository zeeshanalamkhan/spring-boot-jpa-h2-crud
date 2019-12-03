package com.zeeshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
