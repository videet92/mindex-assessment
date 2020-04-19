package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String id);
    Compensation update(Compensation compensation);
}
