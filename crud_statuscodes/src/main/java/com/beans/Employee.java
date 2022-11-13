package com.beans;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
 

 
public class Employee 
{
	 
	private Integer id;
	@JsonProperty("user_name")
	@Size(min = 2, message="not .. ") 
	private String name;
	
	@Past
	@JsonProperty("user_BirthDay")
 	private Date birthDate;
	public Employee()
 	{
 		
 	}
	
 	public Employee(Integer id, String name, Date birthDate) 
	{
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}	
 	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
		  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) 
	{
		this.birthDate = birthDate;
	}
	
	
}
