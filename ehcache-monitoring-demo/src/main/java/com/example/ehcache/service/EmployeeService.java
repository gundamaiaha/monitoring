package com.example.ehcache.service;

import com.example.ehcache.exception.EmployeeNotFoundException;
import com.example.ehcache.model.Employee;
import com.example.ehcache.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public Employee createEmployee(final Employee employee) {
        log.info("creating the employee");
        return employeeRepository.save(employee);
    }

    @Cacheable(value = "employees")
    public Employee getEmployee(final Long empId) {
        log.info("getting the employee details from DB");
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException(empId));
    }


}
