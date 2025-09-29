package com.aurionpro;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aurionpro.dao.EmployeeDao;
import com.aurionpro.entity.Employee;
import com.aurionpro.entity.Student;

//@SpringBootApplication
public class EmployeeMainHibernateJpaCrud {

//	public static void main(String[] args) {
//		SpringApplication.run(EmployeeMainHibernateJpaCrud.class, args);
//	}
    
	@Bean
	public CommandLineRunner commandLineRunner(EmployeeDao employeeDao) {
		return runner -> {
            createEmployee(employeeDao);
//            getEmployeeById(employeeDao);
//            getAllEmployee(employeeDao);
//            updateEmployeeName(employeeDao);
//            deleteEmployeeById(employeeDao);
		};
	}

	private void createEmployee(EmployeeDao employeeDao) {
         Employee employee = new Employee("Aradhya", 1000.0);
         employeeDao.createEmployee(employee);
         System.out.println("Sucessfully Created Student");
	}

	private void getEmployeeById(EmployeeDao employeeDao) {
         Employee employee = employeeDao.getEmployeeById(1);
         System.out.println(employee);
	}

	private void getAllEmployee(EmployeeDao employeeDao) {
         List<Employee> employees = employeeDao.getAllEmployee();
         for(Employee employee : employees) {
        	 System.out.println(employee);
         }
	}

	private void updateEmployeeName(EmployeeDao employeeDao) {
         employeeDao.updateEmployeeName(1, "Danny");
         System.out.println("Sucessfully updated employee");
	}

	private void deleteEmployeeById(EmployeeDao employeeDao) {
         employeeDao.deleteEmployeeById(1);
         System.out.println("Sucessfully deleted employee");
	}


}
