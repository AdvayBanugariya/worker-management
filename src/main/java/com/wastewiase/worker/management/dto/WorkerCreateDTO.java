package com.wastewiase.worker.management.dto;

import com.wastewiase.worker.management.enums.WorkerStatus;
import lombok.Data;

import java.util.Date;
@Data
public class WorkerCreateDTO {
    private String name;
    private String contactNumber;
    private String contactEmail;
    private String roleId;
    private String createdBy;
    private WorkerStatus workerStatus;
    private Date createdDate;
}
