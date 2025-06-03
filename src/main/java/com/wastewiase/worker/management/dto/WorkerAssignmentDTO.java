package com.wastewiase.worker.management.dto;

import com.wastewiase.worker.management.enums.Shift;
import lombok.Data;

import java.util.Date;

@Data
public class WorkerAssignmentDTO {
    private String assignmentId;
    private String workerId;
    private String zoneId;
    private String routeId;
    private Shift shift;
    private String createdBy;
    private Date createdDate;
}
