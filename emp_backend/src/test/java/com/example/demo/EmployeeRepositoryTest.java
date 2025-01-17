package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest

@TestPropertySource(locations = "classpath:application-test.properties")
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;
    @BeforeEach
    public void setup() {
        repository.deleteAll(); // Ensure a clean slate for each test

    }

    @Test
    void testFindById() {
        Employee employee = new Employee(
                "Jane",           // First name
                "Doe",            // Last name
                "jane.doe@example.com", // Email
                80000,            // Salary
                "HR",             // Department
                "Manager",        // Designation
                LocalDate.of(2020, 3, 15)
        );
        Employee savedEmployee = repository.save(employee);
        Long generatedId = savedEmployee.getId();
        Optional<Employee> saved = repository.findById(generatedId);
        assertTrue(saved.isPresent());
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee("John",           // First name
                "Doe",            // Last name
                "john.doe@example.com", // Email
                75000,            // Salary
                "IT",             // Department
                "Software Engineer", // Designation
                LocalDate.of(2022, 5, 10));
        Employee saved = repository.save(employee);
        assertNotNull(saved);
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee("John1",           // First name
                "Doe",            // Last name
                "john1.doe@example.com", // Email
                75000,            // Salary
                "IT",             // Department
                "Software Engineer", // Designation
                LocalDate.of(2022, 5, 10));
        Employee saved = repository.save(employee);
        // Ensure the employee is saved successfully
        Optional<Employee> found = repository.findById(saved.getId());
        assertTrue(found.isPresent(), "Employee should be present before deletion");

        // Step 2: Delete the employee
        repository.delete(saved);

        // Step 3: Assert the employee is no longer present
        Optional<Employee> deleted = repository.findById(saved.getId());
        assertFalse(deleted.isPresent(), "Employee should not be present after deletion");
    }
}

