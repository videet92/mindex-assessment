package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * The Data Access Object contract for {@link Compensation} entity
 *
 * @author
 */
public interface CompensationRepository extends MongoRepository<Compensation, String>{
    /**
     * The method to return the {@linkplain Compensation compensation} entity based on Employee id
     * @param employeeId the id of {@linkplain com.mindex.challenge.data.Employee } class
     * @return the found {@linkplain Compensation compensation} object
     */
    @Query(value = "{'employee.employeeId': ?0}")
    Compensation findCompensationByEmployeeId(String employeeId);
}
