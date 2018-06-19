package com.mycompany.eventmanagement.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.eventmanagement.model.BasePO;
import com.mycompany.eventmanagement.model.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao{

 private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void insert(BasePO obj) throws IOException {
		Session session=null;
		try{
			session = this.sessionFactory.openSession();		
			session.save(obj);
			session.flush();
		}finally{
			if(session !=null) 	session.close();
		}
		
	}

	@Override
	public void delete(BasePO obj) throws IOException{
		Session session=null;
		try{
			session = this.sessionFactory.openSession();		
			 session.delete(obj);
			 session.flush();
		}finally{
			if(session !=null) 	session.close();
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees=null;
		Session session=null;
		try{
			session = this.sessionFactory.openSession();		
			 employees= session.createQuery("from Employee").list();
		}finally{
			if(session !=null) 	session.close();
		}
		return employees;
	}

	@Override
	public void update(BasePO obj) throws IOException {
			Session session=null;
			try{
				session = this.sessionFactory.openSession();		
				session.update(obj);
				session.flush();
				}finally{
					if(session !=null) 	session.close();
				}
			
		
	}
	
	@Override
	public void delete(Long empId) throws IOException{
		Session session=null;
		try{
			session = this.sessionFactory.openSession();	
			Employee emp = (Employee)session.load(Employee.class,empId);
		    
			 session.delete(emp);
			 session.flush();
		}finally{
			if(session !=null) 	session.close();
		}
	}

	@Override
	public Employee getEmployee(Long empId){
		Session session=null;
		try{
			session = this.sessionFactory.openSession();				
			Criteria cr = session.createCriteria(Employee.class);
			cr.add(Restrictions.eq("MId", empId));
			cr.setFetchMode("events", FetchMode.JOIN);
			List<Employee> response= cr.list();
			if(response != null && !response.isEmpty()) return response.get(0);
		}finally{
			if(session !=null) 	session.close();
		}
		return null;
	}

}
