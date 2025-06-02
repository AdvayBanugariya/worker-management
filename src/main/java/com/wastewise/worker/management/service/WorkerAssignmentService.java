package com.wastewise.worker.management.service;

import com.wastewise.worker.management.model.WorkerAssignment;
import com.wastewise.worker.management.repository.WorkerAssignmentRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkerAssignmentService {
    private final WorkerAssignmentRepository workerAssignmentRepository;

    public WorkerAssignmentService(WorkerAssignmentRepository workerAssignmentRepository){
        this.workerAssignmentRepository = workerAssignmentRepository;
    }

    public String deleteWorkerAssignment(String id){
        return null;
        //also change the status of the worker from available to occupied
    }
}
