package com.mindex.challenge.data;

import lombok.Getter;
import lombok.Setter;

/**
 * The Reporting Structure consists of Employee and the umber of Reports under the employee
 */
@Getter
@Setter
public class ReportingStructure {
    private Employee employee;
    private Integer numberOfReports;
}
