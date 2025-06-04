package com.wastewise.worker.management.dto;

import com.wastewise.worker.management.enums.WorkerStatus;
import lombok.Data;

import java.util.Date;

@Data
public class WorkerUpdateDTO {
    private String name;
    private String contactNumber;
    private String contactEmail;
    private String roleId;
    private String updatedBy;
    private WorkerStatus workerStatus;
    private Date updatedDate;
}