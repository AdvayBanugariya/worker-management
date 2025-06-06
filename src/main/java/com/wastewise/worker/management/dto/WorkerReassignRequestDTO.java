package com.wastewise.worker.management.dto;

import lombok.Data;

@Data
public class WorkerReassignRequestDTO {
    private String oldWorkerId1;
    private String oldWorkerId2;
    private String newWorkerId1;
    private String newWorkerId2;
}
