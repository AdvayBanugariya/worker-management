package com.wastewiase.worker.management.dto;

import com.wastewiase.worker.management.enums.WorkerStatus;
import lombok.Data;

@Data
public class WorkerDTO {
    private String id;
    private String name;
    private String contactNumber;
    private String contactEmail;
    private WorkerStatus workerStatus;
}
