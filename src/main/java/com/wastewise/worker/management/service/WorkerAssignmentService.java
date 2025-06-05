package com.wastewise.worker.management.service;

public interface WorkerAssignmentService {

    String updateSingleWorkerAssignment(String assignmentId, String oldWorkerId, String newWorkerId);

    String deleteWorkerAssignment(String assignmentId);

    String assignWorkertoAssignment(String assignmentId, String workerId);
}
