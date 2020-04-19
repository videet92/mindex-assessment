package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Service to manage the {@link ReportingStructure} entities
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Find the {@linkplain ReportingStructure reportingStructure} object with specified id
     * @param id The id of the {@linkplain com.mindex.challenge.data.Employee} to find compensation
     * @return the found {@linkplain ReportingStructure reportingStructure} object
     */
    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating ReportingStructure with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();

        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(findReportsCount(employee)-1);

        return reportingStructure;
    }

    /**
     * The Recursive method to calculate the number of reports for a particular employee. If no reports it returns 0
     * @param employee the {@linkplain Employee employee} entity for which number of reports needs to be counted
     * @return the value of the number of reports
     */
    private int findReportsCount(Employee employee) {

        //base condition, if the employee has no report
        if(employee.getDirectReports() == null)
            return 1;

        int maxNumOfReports = 0;

        //looping through all the direct reports of the employee
        for(Employee reports:employee.getDirectReports()) {

            //the database call to fetch the employee
            Employee emp = employeeRepository.findByEmployeeId(reports.getEmployeeId());
            if (emp == null) {
                //if employee is not present in the database
                throw new RuntimeException("Invalid employeeId: " + reports.getEmployeeId());
            }
            maxNumOfReports+= findReportsCount(emp);
        }
        return maxNumOfReports+1;
    }
}
