package com.crud_status;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;
import com.beans.Employee;

@Component
public class EmpDaoService {

	private static List<Employee> employees = new ArrayList<>();
	private static int usersCount = 3;
	
	static
	{
		employees.add(new Employee(1, "Sagar", new Date()));
		employees.add(new Employee(2, "Mayuri", new Date()));
		employees.add(new Employee(3, "Shaheen", new Date()));
		
	}

	
	public List<Employee> findAll() 
	{
		return employees;
	}
	 

	public Employee save(Employee emp) // post 
	{
		if (emp.getId() == null) 
 		{
 			emp.setId(++usersCount);
 		}
		
		employees.add(emp);
		
		return emp;
	}
	 

	public Employee findOne(int id)
	{
		for (Employee emp : employees) 
		{
			if (emp.getId() == id) 
			{
				return emp;
			}
		}
		
		return null;
	}
	
	 	
public Employee deleteById(int id) 
{
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) 
		{
			Employee emp = iterator.next();
			if (emp.getId() == id) 
			{
				iterator.remove();
				return emp;
			}
		}
		return null;
}


	
public Employee updateEmployee(Employee empl) 
{
	
Iterator<Employee> iterator = employees.iterator();
while (iterator.hasNext()) 
{
	Employee emp = iterator.next();
	
	if (emp.getId() == empl.getId()) 
	{
		emp.setId(empl.getId());
		emp.setName(empl.getName());
		emp.setBirthDate(empl.getBirthDate());
		
		return emp;
	}
	
	
}
return null;
}
 

}
