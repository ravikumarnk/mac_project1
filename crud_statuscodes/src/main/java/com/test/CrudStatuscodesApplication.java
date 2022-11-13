package com.test;

// <version>2.6.13</version>
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 

@SpringBootApplication(scanBasePackages = {"com"}) 
public class CrudStatuscodesApplication 
{
	
	public static void main(String[] args) 
	{
		SpringApplication.run(CrudStatuscodesApplication.class, args);
	}

}
