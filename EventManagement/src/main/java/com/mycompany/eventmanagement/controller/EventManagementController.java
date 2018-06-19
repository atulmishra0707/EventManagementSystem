package com.mycompany.eventmanagement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.eventmanagement.EventManagementException;
import com.mycompany.eventmanagement.model.Employee;
import com.mycompany.eventmanagement.model.Event;
import com.mycompany.eventmanagement.model.EventRegister;
import com.mycompany.eventmanagement.service.EmployeeService;
import com.mycompany.eventmanagement.service.EventService;

@Controller
public class EventManagementController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private EmployeeService employeeService;
	
	
   private static final Logger logger =LoggerFactory.getLogger(EventManagementController.class);

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}


	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	


	@RequestMapping(value="/addEvent" ,method=RequestMethod.GET)
	public String addEvent(Model model){
		Event event = new Event();
		model.addAttribute("event", event);
		return "addEvent";
	}
	
	
	@RequestMapping(value = "/addEventDetails", method = RequestMethod.POST)
	public String newEventAdd(@ModelAttribute("event") Event  event, Model model) {
	
		try {
			if(validate(event)){
				eventService.addEvent(event);
			}
		} catch (EventManagementException e) {
			logger.error("Error Adding Event" + event);
			e.printStackTrace();
			model.addAttribute("message", "Error Adding Event, due to :- " + e.getMessage());
			return "error";
		}catch(Exception ex){
			logger.error("Error in Adding event" );
			model.addAttribute("message", ex.getMessage());
			ex.printStackTrace();
			return "error";
		}
		model.addAttribute("message", "Event Added Successfully");
		return "confirmation";
	}
	
	@RequestMapping(value="/registerEmployeeForEventPage" ,method=RequestMethod.GET)
	public String registerEmployeeForEventPage(Model model){
		try{
			model.addAttribute("eventList", eventService.getAllEvents());
			model.addAttribute("empList", employeeService.getAllEmployees());
			return "registerEmployeeForEvent";
		}catch(Exception ex){
			logger.error("Error in getting employee and event list" );
			model.addAttribute("message", ex.getMessage());
			ex.printStackTrace();
			return "error";
		}
	}
	
	
	
	@RequestMapping(value="/registerEmployeeForEvent" ,method=RequestMethod.POST)
	public String registerEmployeeForEvent(@ModelAttribute("EventRegister")EventRegister eventRegister, Model model){
		
		try {
			if(validate(eventRegister)){
				Employee emp = employeeService.getEmployee(eventRegister.getmId());
				List<Event> events= eventService.getEvents(eventRegister.getEvents());
				emp.getEvents().addAll(events);
				employeeService.updateEmployee(emp);
				model.addAttribute("msg", "Employee Registered for Events sucessfully");
			}
		} catch (EventManagementException e) {
			logger.error("Error in registering for event" );
			model.addAttribute("msg", "Employee Event Registration Failed, due to :- " + e.getMessage());
			e.printStackTrace();
		}catch(Exception ex){
			logger.error("Error in registering for event" );
			model.addAttribute("message", ex.getMessage());
			ex.printStackTrace();
			return "error";
		}
		
		
		model.addAttribute("eventList", eventService.getAllEvents());
		model.addAttribute("empList", employeeService.getAllEmployees());
		return "registerEmployeeForEvent";
	}
	
	
	@RequestMapping(value="/showAllEmployees" ,method=RequestMethod.GET)
	public String showAllEmployeesDetails(Model model){
		try{
			List<Employee> employees =employeeService.getAllEmployees();
			model.addAttribute("employees", employees);
			return "showAllEmployees";
		}catch(Exception ex){
			logger.error("Error listing Employees" );
			model.addAttribute("message", ex.getMessage());
			ex.printStackTrace();
			return "error";
		}
	}

	
	@RequestMapping(value="/addEmployeePage" , method=RequestMethod.GET)
	public String getAddEmployeePage(Model model){
		
		Employee employee= new Employee();
		model.addAttribute("employee", employee);
		return  "addEmployee";
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") Employee emp, Model model) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date joinDate=format.parse(emp.getDate());
			emp.setJoinDate(joinDate);
		}catch(ParseException e){
			logger.error("Error parsing Join Date" );
			model.addAttribute("message", "Error Parsing Date..Please provide correct Date");
			return "error";
			
		}
		try {
			if(validate(emp)){
				employeeService.addEmployee(emp);
			}
		} catch (EventManagementException e) {
			logger.error("Error Adding Employee" );
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "error";
			
		}catch(Exception ex){
			logger.error("Error Adding Employee" );
			model.addAttribute("message", ex.getMessage());
			ex.printStackTrace();
			return "error";
		}
		model.addAttribute("message", "Employee Added Successfully");
		return "confirmation";
	}
		
	private boolean validate(Object obj) throws EventManagementException {
		
		if(obj instanceof Employee){
			Employee.validate((Employee) obj);
		}else if(obj instanceof Event){
			Event.validate((Event) obj);
		}else if(obj instanceof EventRegister){
			EventRegister.validate((EventRegister) obj);
		}
		
		return true;
	}
}
