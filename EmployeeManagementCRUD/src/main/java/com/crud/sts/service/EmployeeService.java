package com.crud.sts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.sts.Repository.EmployeeRepository;
import com.crud.sts.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public Employee addEmployee(Employee employee) {
		return repository.save(employee);
	}

	public List<Employee> addEmployees(List<Employee> employees) {
		return repository.saveAll(employees);
	}

	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	public Employee getEmployeeById(int id) {
		return repository.findById(id).orElse(null);
	}

	public String deleteEmployee(int id) {
	    Optional<Employee> employeeOptional = repository.findById(id);
	    if (employeeOptional.isPresent()) {
	        repository.deleteById(id);
	        return id + " deleted successfully";
	    } else {
	        return id + " id not found";
	    }
	}

	public Employee updateEmployee(Employee employee, int id) {
		Employee updateEmp = repository.findById(id).orElse(null);
		updateEmp.setEmployeeName(employee.getEmployeeName());
		updateEmp.setAge(employee.getAge());
		updateEmp.setEmail(employee.getEmail());
		updateEmp.setDesignation(employee.getDesignation());
		return repository.save(updateEmp);
	}

}
