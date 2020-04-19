package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The RESTful service for {@link Compensation} entities
 *
 * @author
 */
@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Create a new {@linkplain Compensation compensation} and save it to the database
     * @param compensation the {@linkplain Compensation compensation} to create
     * @return the created {@linkplain Compensation compensation} object
     */
    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }

    /**
     * Find the {@linkplain Compensation compensation} object with specified id
     * @param id The id of the {@linkplain com.mindex.challenge.data.Employee employee} to find compensation
     * @return the found {@linkplain Compensation compensation} object
     */
    @GetMapping("/compensation/employee/{id}")
    public Compensation findCompensation(@PathVariable String id) {
        LOG.debug("Received compensation find request for id [{}]", id);

        return compensationService.read(id);
    }
}
