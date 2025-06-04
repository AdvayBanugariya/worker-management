package com.wastewise.worker.management.dto;

import com.wastewise.worker.management.enums.Shift;
import lombok.Data;

@Data
public class WorkerAssignmentDTO {
    private String assignmentId;
    private String workerId;
    private String zoneId;
    private String routeId;
    private Shift shift;
    private String createdBy;
}