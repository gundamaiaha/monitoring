package com.example.ehcache.controller;

import com.example.ehcache.model.Employee;
import com.example.ehcache.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody final Employee employee) {
        log.info("calling the service to create an employee");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employee));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long empId) {
        log.info("calling the service the employee with id : {}", empId);
        return ResponseEntity.ok(employeeService.getEmployee(empId));

    }


}
