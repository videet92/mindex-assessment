package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Service to manage the {@link Compensation} entities
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * The service method to create the {@linkplain Compensation compensation} entity
     * @param compensation the {@linkplain Compensation compensation} entity to be created
     * @return the created {@link Compensation compensation} entity created
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        try {
            String id = compensation.getEmployee().getEmployeeId();
            compensation.setEmployee(employeeRepository.findByEmployeeId(id));
            compensationRepository.insert(compensation);
        }catch (RuntimeException e) {
            throw new RuntimeException("Employee not found");
        }

        return compensation;
    }

    /**
     * Find the {@linkplain Compensation compensation} object with specified id
     * @param id The id of the {@linkplain com.mindex.challenge.data.Employee employee} to find compensation
     * @return the found {@linkplain Compensation compensation} object
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Searching compensation with id [{}]", id);
        Compensation compensation = null;
        try {
            compensation = compensationRepository.findCompensationByEmployeeId(id);
        }catch(RuntimeException e) {
            e.printStackTrace();
        }
        return compensation;
    }

    /**
     * The service method to update the {@linkplain Compensation compensation} entity
     * @param compensation the {@linkplain Compensation compensation} entity to update
     * @return the updated {@linkplain Compensation compensation} entity
     */
    @Override
    public Compensation update(Compensation compensation) {
        /**
         * The method is created for future purpose
         */
        return null;
    }
}
