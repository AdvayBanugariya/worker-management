package com.wastewiase.worker.management.dto;

import com.wastewiase.worker.management.enums.WorkerStatus;

public class WorkerUpdateDTO {
    private String name;
    private String contactNumber;
    private String contactEmail;
    private String roleId;
    private String updatedBy;
    private WorkerStatus workerStatus;
}
