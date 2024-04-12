package com.viraj.springboot.curdDemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.viraj.springboot.curdDemo.entity.Employee;
import com.viraj.springboot.curdDemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	//quick and dirty : inject employee dao
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployService) {
		employeeService = theEmployService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	

	
	//expose "/employees" and return a list of employees
	
	//add mapping for GET /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null) {
			throw new RuntimeException("Employee id not found.."+employeeId);
		}
		return theEmployee;
	}
	
	
	// add mapping for POST /employee - add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		//also just in casse they pass an id in JSON .. set id to 0
		
		//this is to force a save of new item.. instead of update
		
		theEmployee.setId(0);
		Employee dbEmployee = employeeService.save(theEmployee);
		return dbEmployee;
	}
	
	
	//add mapping for PUT /employees - update the exisiting employee
	@PutMapping("/employees")
	public Employee updatEmployee(@RequestBody Employee theEmployee) {
		Employee dbEmployee = employeeService.save(theEmployee);
		return dbEmployee;
	}
	
	//add mapping for delete /employees/{employeeId} - delete employee
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.findById(employeeId);
		//throw excepton if null
		
		if(tempEmployee == null) {
			throw new RuntimeException("Employee id not found.."+employeeId
					);
		}
		
		employeeService.deleteById(employeeId);
		return "Deleted Employee with id : "+employeeId;
	}
	
	
	

}
