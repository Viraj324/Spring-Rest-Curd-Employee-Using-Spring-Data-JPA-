package com.viraj.springboot.curdDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viraj.springboot.curdDemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	//that's it.. no need too 
}
