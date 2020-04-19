package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The RESTful service for {@link Employee} entities
 *
 * @author
 */
@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Create a new {@linkplain Employee employee} and save it to the database
     * @param employee the {@linkplain Employee employee} to create
     * @return the created {@linkplain Employee employee} object
     */
    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    /**
     * Find the {@linkplain Employee employee} object with specified id
     * @param id The id of the {@linkplain Employee employee} to find employee
     * @return the found {@linkplain Employee employee} object
     */
    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee read request for id [{}]", id);

        return employeeService.read(id);
    }

    /**
     * Update the {@linkplain Employee employee} object with specified id
     * @param id The id of the {@linkplain Employee employee} to update compensation
     * @param employee the {@linkplain Employee employee} to update
     * @return the updated {@linkplain Employee employee} object
     */
    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee update request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
}
