package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The Data Access Object contract for {@link Employee} entity
 *
 * @author
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    /**
     * Find {@linkplain Employee employee} object by id
     * @param employeeId the employee id for finding the {@linkplain Employee employee} object
     * @return the found {@linkplain Employee employee} object
     */
    Employee findByEmployeeId(String employeeId);
}
