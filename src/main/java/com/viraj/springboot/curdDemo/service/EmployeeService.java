package com.viraj.springboot.curdDemo.service;
import java.util.*;
import com.viraj.springboot.curdDemo.entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	
	Employee findById(int theId);
	
	Employee save(Employee theEmployee);
	
	void deleteById(int theId);
	

}
