package com.crud_status;

import java.net.URI;

// https://bushansirgur.in/spring-getmapping-postmapping-putmapping-deletemapping-and-patchmapping-annotation-with-examples/
// https://codezone4.wordpress.com/2012/11/08/restful-web-services-java-mysql-and-json/
// https://bushansirgur.in/spring-getmapping-postmapping-putmapping-deletemapping-and-patchmapping-annotation-with-examples/

//localhost:6666/ 

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.sql.Date;
import java.util.List;

import javax.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.beans.Employee;
import com.crud.exception.UserNotFoundException;
 
 
 
 
// localhost:6666/project/ 
 

@RestController
@RequestMapping("/project")
public class EmployeeResources {
  
	
	@Autowired
	private EmpDaoService service;   
	 
	
	@RequestMapping(method = RequestMethod.GET, path="/restapi")
	public String message()
	{
		
		return "We are NTT DATA PEOPLE.. ";
	}
		
	
	@GetMapping(path = "/example1")
	public String getMessage()
	{
		return "Welcome to NTT DATA (using @Getmapping)";
	}
		
	@GetMapping(path = "/beandemo")
	public Employee getBean() 
	{
		return new Employee(11, "ravikumar", new Date(12-12-1988));   
	}
		 
	
	// ============ get demo=============
 	// localhost:8088/project/employee
	
	@GetMapping("/employee")
	public List<Employee> retrieveAllEmployees()  
	{
		return service.findAll();
			 
	} 
	  
@GetMapping("/employee/{id}")
public EntityModel<Employee> retrieveEmployee(@PathVariable int id) 
{
	  Employee emp = service.findOne(id);
	  if(emp==null) throw new UserNotFoundException("id: "+ id);
	  EntityModel<Employee> entityModel = EntityModel.of(emp);
	  WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getBean());
	  entityModel.add(linkBuilder.withRel("all-employees"));
	  return entityModel;
}
	  
	  
	  
	
	
	
	/*
	// 2. get       	// 200  
	  @GetMapping("/employee/{id}") // 1.
	  public Employee retrieveEmployee(@PathVariable int id) 
	  {
		  Employee emp = service.findOne(id);
		  if(emp==null) throw new UserNotFoundException("id: "+ id);
		   return emp;
		   
	  }
	   */
	  
	  
	  
	  
	  @PostMapping("/employee") //2.
	  public Employee createUser1(@Valid @RequestBody Employee  employee)  
	  {
	  Employee savedUser = service.save(employee); 
	  return savedUser; 
	  }
	  
	  
	  
// ============ post demo=============
// post, (body raw),   json
// localhost:8666/addemployee    
// {
//      "id": 1,
//      "name": "Sagar",
//      "birthDate": "2022-10-23"
//  }
//	  
	  
		
		  
		  
	  
		  @DeleteMapping("/employee/{id}")
		  public void deleteUser(@PathVariable int id)
		  {
		  	 service.deleteById(id);
		  		 
		  }
	// ============ post demo=============
	// post, (body raw),   json
	// localhost:8666/addemployee 
	  
	// {
	//     "id": 1,
    //     "name": "Sagar",
    //	   "birthDate": "2022-10-23"
	// }
	  @PutMapping("/employee")
	  public void update(@RequestBody Employee employee)
	  {
	  	 service.updateEmployee(employee);
	  	 
	  }
	   
	  
	   @GetMapping("/testnulldata") // ? 
	   public List<Employee> retrieveAllUser() 
	   {
	 		return null;
	   }
	   
	   @GetMapping("/status") // 200
	    ResponseEntity<String> home() {
	        return ResponseEntity.status(HttpStatus.OK).body("OK");
	    }
	    @GetMapping("/notfound") // 404 
	    ResponseEntity<String> notfound() {
	    	return  ResponseEntity.notFound().build();
			 		 
	    }
	    @GetMapping("/badrequest") // 400
	    ResponseEntity<String> badRequest() {
	        return ResponseEntity.badRequest().body("Bad request");
	    };
	      
	    /// improvised method ... 
	    
		  // Using ResponseEntity // 201 
		  @PostMapping("/addemployee_re")
		  public ResponseEntity<Employee> save(@Valid @RequestBody Employee empl) // // validation on model will be automatically invoked when the binding happens
		  {
		  	try 
		  	{
		  	    return new ResponseEntity<>(service.save(empl), HttpStatus.CREATED); 
		  	} catch (Exception e) 
		  	{
		  		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  	}
		  }
		   
		  
		  // with address response 
		  @PostMapping("/employee_re") 
		  public ResponseEntity<Object> createUserwithlocation(@RequestBody Employee employee) 
		  { 
			  	Employee savedUser = service.save(employee);
			  	
			  	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(savedUser.getId())
				  .toUri();
		  		return ResponseEntity.created(location).body(location);
		  }
		  

@PostMapping("/usersvalidation")
public ResponseEntity<Object> createUser(@Valid @RequestBody Employee emp) 
{

	Employee savedUser = service.save(emp);
	
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedUser.getId()).toUri();
	return ResponseEntity.created(location).build();

}


 





		  
		  	// Using ResponseEntity
			@GetMapping("/allemployees_re")
			public ResponseEntity<List<Employee>> getAllCustomers() 
			{
				try
				{
					List<Employee> list = service.findAll(); 
					// list.clear();
					 //list = null;
					if (list.isEmpty() || list.size() == 0) 
					{
						return new ResponseEntity< List<Employee> > (HttpStatus.NO_CONTENT);
					}
					return new ResponseEntity< List<Employee> > (list, HttpStatus.OK);
				}
				catch (Exception e) 
				{
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			     }
			}
			
			
			  @GetMapping("/listemployee/{id}") 
			  public Employee getAllCustomerss(@PathVariable int id) 
			  {
				  Employee emp = service.findOne(id);
					
					  if(emp==null) 
					  {
						  throw new UserNotFoundException("id is not found !"+id);
					  
					  }
					 
 				  return emp;
				  			   
			  }
			 
			  
			  
			@PutMapping("/updateemployee_re")//     /{id}   ?
			public ResponseEntity<Employee> updateEmp(@RequestBody Employee empl)
			{
				try
				{
					return new ResponseEntity<Employee>(service.updateEmployee(empl), HttpStatus.OK);
				} catch (Exception e)
				{
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			  
			
			@DeleteMapping("/deleteemployee_re/{id}")
			public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id) {
				
				try {
				     Employee  empl = service.findOne(id);
				 	 service.deleteById(id);
					 return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
					 }
				catch (Exception e) 
				{
					return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			 
			 
			
	
}
