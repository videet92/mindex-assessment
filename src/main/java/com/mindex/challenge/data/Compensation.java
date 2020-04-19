package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The Compensation comprises of the Employee details, salary of the employee and the effective date
 */
@Getter
@Setter
public class Compensation {

    private Employee employee;
    private Integer salary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd-HH:mm")
    private Date effectiveDate;
}
