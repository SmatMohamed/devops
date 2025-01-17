package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class EmployeeServiceTest {

    private final EmployeeService service;
    @Autowired
    public EmployeeServiceTest (EmployeeService service){
        this.service=service;
    }

    @BeforeEach
    public void setup() {
        Employee emp = new Employee("John1",           // First name
                "Doe1",            // Last name
                "john1.doe@example.com", // Email
                75000,            // Salary
                "IT",             // Department
                "Software Engineer", // Designation
                LocalDate.of(2022, 5, 11));
        this.service.createEmployee(emp);
         // Ensure a clean slate for each test

    }

    @Test
    void testCreateEmployee() {
        Employee emp = new Employee("John",           // First name
                "Doe",            // Last name
                "john.doe@example.com", // Email
                75000,            // Salary
                "IT",             // Department
                "Software Engineer", // Designation
                LocalDate.of(2022, 5, 11));
        Employee saved = this.service.createEmployee(emp);
        assertNotNull(saved);
    }

    @Test
    void testFindAllEmployees() {
        List<Employee> employees = this.service.getAllEmployees();
        assertFalse(employees.isEmpty());
    }
}

