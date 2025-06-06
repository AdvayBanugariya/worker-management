package com.wastewise.worker.management.dto;

import lombok.Data;

@Data
public class UpdateWorkerAssignDTO {
    private String oldWorkerId;
    private String newWorkerId;
}
