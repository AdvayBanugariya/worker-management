package com.wastewiase.worker_management.service;


import com.wastewiase.worker_management.repository.WorkerAssignmentRepository;
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