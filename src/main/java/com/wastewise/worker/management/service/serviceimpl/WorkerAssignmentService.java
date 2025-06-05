package com.wastewise.worker.management.service.serviceimpl;

import com.wastewise.worker.management.enums.WorkerStatus;
import com.wastewise.worker.management.exception.ResourceNotFoundException;
import com.wastewise.worker.management.exception.WorkerNotFoundException;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.model.WorkerAssignment;
import com.wastewise.worker.management.model.WorkerAssignmentId;
import com.wastewise.worker.management.repository.WorkerAssignmentRepository;
import com.wastewise.worker.management.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkerAssignmentService implements com.wastewise.worker.management.service.WorkerAssignmentService {
    private final WorkerAssignmentRepository workerAssignmentRepository;

    private final WorkerRepository workerRepository;

    public WorkerAssignmentService(WorkerAssignmentRepository workerAssignmentRepository, WorkerRepository workerRepository){
        this.workerAssignmentRepository = workerAssignmentRepository;
        this.workerRepository = workerRepository;
    }

    @Transactional
    public String assignWorkertoAssignment(String assignmentId, String workerId) throws WorkerNotFoundException {
        // Step 1: Fetch the worker
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new WorkerNotFoundException("Worker not found with ID: " + workerId));

        if (worker.getWorkerStatus() != WorkerStatus.AVAILABLE) {
            throw new IllegalStateException("Worker is not available for assignment");
        }

        WorkerAssignment assignment = new WorkerAssignment();
        assignment.setId(new WorkerAssignmentId(assignmentId, workerId));
        assignment.setCreatedDate(LocalDateTime.now());

        workerAssignmentRepository.save(assignment);

        worker.setWorkerStatus(WorkerStatus.OCCUPIED);
        workerRepository.save(worker);

        log.info("Assigned worker {} to assignment {}", workerId, assignmentId);
        return "Worker assigned successfully";
    }


    @Transactional
    public String deleteWorkerAssignment(String assignmentId) {
        List<WorkerAssignment> assignments = workerAssignmentRepository.findByAssignmentId(assignmentId);

        if (assignments.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for assignmentId: " + assignmentId);
        }

        // Collect all worker IDs linked to this assignment
        List<String> workerIds = assignments.stream()
                .map(a -> a.getId().getWorkerId()) // assuming @EmbeddedId
                .collect(Collectors.toList());

        // Delete all assignments with the given assignmentId
        workerAssignmentRepository.deleteAll(assignments);
        log.info("Deleted all assignments with assignmentId {}", assignmentId);

        // Update status of all involved workers
        List<Worker> workers = workerRepository.findAllById(workerIds);
        for (Worker worker : workers) {
            worker.setWorkerStatus(WorkerStatus.OCCUPIED);
        }
        workerRepository.saveAll(workers);
        log.info("Updated {} workers to status OCCUPIED", workers.size());

        return "Deleted assignments and updated worker statuses";
    }





}