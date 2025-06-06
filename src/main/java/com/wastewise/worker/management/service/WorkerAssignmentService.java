package com.wastewise.worker.management.service;

import com.wastewise.worker.management.dto.WorkerAssignmentDTO;

public interface WorkerAssignmentService {

    String updateSingleWorkerAssignment(String assignmentId, String oldWorkerId, String newWorkerId);

    String deleteWorkerAssignment(String assignmentId);

    String assignWorkertoAssignment(String assignmentId, String workerId, WorkerAssignmentDTO dto);
}
