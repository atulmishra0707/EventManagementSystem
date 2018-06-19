package com.mycompany.eventmanagement.service;

import java.util.List;

import com.mycompany.eventmanagement.EventManagementException;
import com.mycompany.eventmanagement.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	void addEmployee(Employee emp) throws EventManagementException;
	void updateEmployee(Employee emp) throws EventManagementException;
	void deleteEmployee(Long empId) throws EventManagementException;
	Employee getEmployee(Long empmid) throws EventManagementException;
	
}
