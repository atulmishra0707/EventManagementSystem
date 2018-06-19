package com.mycompany.eventmanagement.dao;

import java.io.IOException;
import java.util.List;

import com.mycompany.eventmanagement.model.Employee;

public interface EmployeeDao extends BaseDao{

	List<Employee> getAllEmployees() throws IOException;

	void delete(Long empId) throws IOException;

	Employee getEmployee(Long empId) throws IOException;
}
