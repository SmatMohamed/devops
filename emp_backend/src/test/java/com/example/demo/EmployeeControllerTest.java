package com.example.demo;


import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {
        // Mocking the service response
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Collections.singletonList(new Employee(
                        "John", "Doe", "john.doe@example.com", 75000,
                        "IT", "Developer", LocalDate.of(2022, 1, 10)
                )));

        // Performing the GET request
        mockMvc.perform(get("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fname").value("John"))
                .andExpect(jsonPath("$[0].lname").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        // Mocking the service response
        Employee mockEmployee = new Employee(
                "Jane", "Smith", "jane.smith@example.com", 80000,
                "HR", "Manager", LocalDate.of(2023, 5, 20)
        );
        Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

        // JSON payload for the POST request
        String employeeJson = """
                {
                    "fname": "Jane",
                    "lname": "Smith",
                    "email": "jane.smith@example.com",
                    "salary": 80000,
                    "department": "HR",
                    "designation": "Manager",
                    "joiningDate": "2023-05-20"
                }
                """;

        // Performing the POST request
        mockMvc.perform(post("/api/v1/employees")
                        .content(employeeJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("Jane"))
                .andExpect(jsonPath("$.lname").value("Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"));
    }

    @Test
    void testGetById() throws Exception {
        // Mocking the service response
        Employee mockEmployee = new Employee(
                "John", "Doe", "john.doe@example.com", 75000,
                "IT", "Developer", LocalDate.of(2022, 1, 10)
        );
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(mockEmployee);

        // Performing the GET request
        mockMvc.perform(get("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("John"))
                .andExpect(jsonPath("$.lname").value("Doe"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        // Mocking the service response
        Employee updatedEmployee = new Employee(
                "John", "Updated", "john.updated@example.com", 85000,
                "IT", "Senior Developer", LocalDate.of(2021, 3, 15)
        );
        Mockito.when(employeeService.updateEmployeeById(Mockito.eq(1L), Mockito.any(Employee.class)))
                .thenReturn(updatedEmployee);

        // JSON payload for the PUT request
        String updateJson = """
                {
                    "fname": "John",
                    "lname": "Updated",
                    "email": "john.updated@example.com",
                    "salary": 85000,
                    "department": "IT",
                    "designation": "Senior Developer",
                    "joiningDate": "2021-03-15"
                }
                """;

        // Performing the PUT request
        mockMvc.perform(put("/api/v1/employees/1")
                        .content(updateJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lname").value("Updated"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        // Mocking the service response
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", true);
        Mockito.when(employeeService.deleteEmployeeById(1L)).thenReturn(response);

        // Performing the DELETE request
        mockMvc.perform(delete("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Deleted").value(true));
    }
}
