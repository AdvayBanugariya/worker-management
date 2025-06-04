package com.wastewise.worker.management.dto;

import com.wastewise.worker.management.enums.WorkerStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WorkerCreateDTO {
    private String name;
    private String contactNumber;
    private String contactEmail;
    private String roleId;
    private String createdBy;
    private WorkerStatus workerStatus;
    private LocalDateTime createdDate;
}