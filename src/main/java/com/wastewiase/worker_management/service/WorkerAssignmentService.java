package com.wastewiase.worker_management.service;


import com.wastewiase.worker_management.exception.ResourceNotFoundException;
import com.wastewiase.worker_management.model.WorkerAssignmentId;
import com.wastewiase.worker_management.repository.WorkerAssignmentRepository;
import org.springframework.stereotype.Service;

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