package com.wastewise.worker.management.service;

import com.wastewise.worker.management.exception.ResourceNotFoundException;
import com.wastewise.worker.management.model.WorkerAssignmentId;
import com.wastewise.worker.management.repository.WorkerAssignmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorkerAssignmentService {
    private final WorkerAssignmentRepository workerAssignmentRepository;

    public WorkerAssignmentService(WorkerAssignmentRepository workerAssignmentRepository){
        this.workerAssignmentRepository = workerAssignmentRepository;
    }

    public String deleteWorkerAssignment(String assignmentId, String workerId) {
        WorkerAssignmentId id = new WorkerAssignmentId(assignmentId, workerId);
        if (workerAssignmentRepository.existsById(id)) {
            workerAssignmentRepository.deleteById(id);
            log.info("Deleted assignment {} for worker {}", assignmentId, workerId);
            return "Deleted";
        } else {
            throw new ResourceNotFoundException("Worker assignment not found");
        }
    }



}