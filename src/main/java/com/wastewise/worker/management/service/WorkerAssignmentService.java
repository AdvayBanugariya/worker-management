package com.wastewise.worker.management.service;

public interface WorkerAssignmentService {

    String deleteWorkerAssignment(String assignmentId);

    String assignWorkertoAssignment(String assignmentId, String workerId);
}
