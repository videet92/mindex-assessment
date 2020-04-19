package com.mindex.challenge.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The Employee entity with various attributes
 */
@Getter
@Setter
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;
}
