package com.mycompany.eventmanagement.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.eventmanagement.EventManagementException;
import com.mycompany.eventmanagement.dao.EmployeeDao;
import com.mycompany.eventmanagement.model.Employee;

public class EmployeeServiceImpl  implements EmployeeService{

	
	@Autowired
	private EmployeeDao employeeDao;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees=null;
		try {
			 employees= employeeDao.getAllEmployees();
		} catch (IOException e) {
			logger.error("Error while getting all Employees");
			e.printStackTrace();
		}
		return employees;
		
	}


	@Override
	public void addEmployee(Employee emp) throws EventManagementException {
		
		try {
			employeeDao.insert(emp);
			logger.info("Employee added sucessfully" + emp);
		} catch (IOException e) {
			logger.error("addEmployee:Error while adding employee " + emp);			
			e.printStackTrace();
			throw new EventManagementException("Error Adding employee");
		}
		
	}


	@Override
	public void updateEmployee(Employee emp) throws EventManagementException {
		try {
			employeeDao.update(emp);
			logger.info("Employee updated sucessfully" + emp);
		} catch (IOException e) {
			logger.error("updateEmployee:Error while updating employee " + emp);			
			e.printStackTrace();
			throw new EventManagementException("Error Updating employee");
		}
	}


	@Override
	public void deleteEmployee(Long empId) throws EventManagementException {
		try {
			employeeDao.delete(empId);
			logger.info("Employee with id " + empId + " deleted successfully" );
		} catch (IOException e) {
			logger.error("deleteEmployee:Error while deleting employee with ID " + empId);			
			e.printStackTrace();
			throw new EventManagementException("Error Deleting employee");
		}
	}


	@Override
	public Employee getEmployee(Long empId) throws EventManagementException {
		try {
			Employee emp=employeeDao.getEmployee(empId);
			return emp;
			} catch (IOException e) 
			{
			logger.error("Error fetching employee " + empId);			
			e.printStackTrace();
			throw new EventManagementException("Error fetching employee");
		}
	}
	
	
	
}
