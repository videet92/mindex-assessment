package com.mindex.challenge.service.impl;

import com.mindex.challenge.DataBootstrap;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {

    private String reportingStructureIdUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private DataBootstrap dataBootstrap;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingStructure/{id}";
        dataBootstrap.init();
    }

    @Test
    public void testRead() {
        Employee testEmployeeParent = new Employee();
        testEmployeeParent.setFirstName("John");
        testEmployeeParent.setLastName("Lennon");
        testEmployeeParent.setDepartment("Engineering");
        testEmployeeParent.setPosition("Development Manager");

        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");

        // CountNumberOfReports checks
        ReportingStructure reportingStructure = restTemplate.getForObject(reportingStructureIdUrl,
                ReportingStructure.class, employee.getEmployeeId());
        assertNotNull(reportingStructure.getNumberOfReports());
        int count = reportingStructure.getNumberOfReports();
        assertEquals(4, count);
    }
}
