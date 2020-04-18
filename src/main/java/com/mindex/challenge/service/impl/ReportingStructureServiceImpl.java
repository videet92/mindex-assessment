package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating ReportingStructure with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();

        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(findReportsCount(employee));

        return reportingStructure;
    }

    public int findReportsCount(Employee employee) {

        int numemployee = 0;
        List<Employee> temp = employee.getDirectReports();
        if (temp == null) {
            return numemployee;
        }
        for (Employee x : temp) {
            Employee e = employeeRepository.findByEmployeeId(x.getEmployeeId());
            if (e == null) {
                throw new RuntimeException("Invalid employeeId: " + x.getEmployeeId());
            }
            numemployee += Math.max(numemployee, findReportsCount(e));

        }

        return numemployee + temp.size();
    }
}
