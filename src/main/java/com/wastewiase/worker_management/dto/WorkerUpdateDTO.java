package com.wastewiase.worker_management.dto;

import com.wastewiase.worker_management.enums.WorkerStatus;

import java.util.Date;

public class WorkerUpdateDTO {
    private String name;
    private String contactNumber;
    private String contactEmail;
    private String roleId;
    private String updatedBy;
    private WorkerStatus workerStatus;
}
