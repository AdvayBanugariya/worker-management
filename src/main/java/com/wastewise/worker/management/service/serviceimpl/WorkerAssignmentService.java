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

    /**
     *
     * @param assignmentId
     * @param workerId
     * @return
     * @throws WorkerNotFoundException
     */
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

    /**
     * Updating an assignment by changing assigned worker to the assignment. Also updates their status
     * @param assignmentId
     * @param newWorkerId
     * @return
     */

    @Transactional
    public String updateSingleWorkerAssignment(String assignmentId, String oldWorkerId, String newWorkerId) {
        WorkerAssignmentId oldId = new WorkerAssignmentId(assignmentId, oldWorkerId);
        WorkerAssignmentId newId = new WorkerAssignmentId(assignmentId, newWorkerId);

        if (!workerAssignmentRepository.existsById(oldId)) {
            throw new ResourceNotFoundException("Old worker is not assigned to this assignment");
        }
        if (workerAssignmentRepository.existsById(newId)) {
            throw new IllegalStateException("New worker is already assigned to this assignment");
        }
        Worker newWorker = workerRepository.findById(newWorkerId)
                .orElseThrow(() -> new WorkerNotFoundException("New worker not found"));

        if (newWorker.getWorkerStatus() != WorkerStatus.AVAILABLE) {
            throw new IllegalStateException("Worker is not available");
        }

        workerAssignmentRepository.deleteById(oldId);
        WorkerAssignment newAssignment = new WorkerAssignment();
        newAssignment.setId(newId);
        newAssignment.setCreatedDate(LocalDateTime.now());
        workerAssignmentRepository.save(newAssignment);

        Worker oldWorker = workerRepository.findById(oldWorkerId)
                .orElseThrow(() -> new WorkerNotFoundException("Old worker not found"));

        oldWorker.setWorkerStatus(WorkerStatus.AVAILABLE);
        newWorker.setWorkerStatus(WorkerStatus.OCCUPIED);
        workerRepository.saveAll(List.of(oldWorker, newWorker));

        return "Worker assignment updated successfully";
    }

    /**
     *
     * @param assignmentId
     * @param oldWorkerId1
     * @param oldWorkerId2
     * @param newWorkerId1
     * @param newWorkerId2
     * @return
     */

    @Transactional
    public String updateBothWorkerAssignments(String assignmentId,
                                              String oldWorkerId1, String oldWorkerId2,
                                              String newWorkerId1, String newWorkerId2) {
        List<WorkerAssignmentId> oldIds = List.of(
                new WorkerAssignmentId(assignmentId, oldWorkerId1),
                new WorkerAssignmentId(assignmentId, oldWorkerId2)
        );

        for (WorkerAssignmentId id : oldIds) {
            if (!workerAssignmentRepository.existsById(id)) {
                throw new ResourceNotFoundException("Assignment not found for worker: " + id.getWorkerId());
            }
        }

         List<String> newWorkerIds = List.of(newWorkerId1, newWorkerId2);
        List<Worker> newWorkers = workerRepository.findAllById(newWorkerIds);

        if (newWorkers.size() != 2) {
            throw new WorkerNotFoundException("One or both new workers not found");
        }

        for (Worker w : newWorkers) {
            if (w.getWorkerStatus() != WorkerStatus.AVAILABLE) {
                throw new IllegalStateException("Worker " + w.getWorkerId() + " is not available");
            }
        }

        // Delete old assignments
        workerAssignmentRepository.deleteAllById(oldIds);

        // Create new assignments
        List<WorkerAssignment> newAssignments = newWorkerIds.stream()
                .map(workerId -> {
                    WorkerAssignment assignment = new WorkerAssignment();
                    assignment.setId(new WorkerAssignmentId(assignmentId, workerId));
                    assignment.setCreatedDate(LocalDateTime.now());
                    return assignment;
                }).toList();

        workerAssignmentRepository.saveAll(newAssignments);

        // Update statuses
        List<String> oldWorkerIds = List.of(oldWorkerId1, oldWorkerId2);
        List<Worker> oldWorkers = workerRepository.findAllById(oldWorkerIds);

        for (Worker w : oldWorkers) {
            w.setWorkerStatus(WorkerStatus.AVAILABLE);
        }
        for (Worker w : newWorkers) {
            w.setWorkerStatus(WorkerStatus.OCCUPIED);
        }

        workerRepository.saveAll(oldWorkers);
        workerRepository.saveAll(newWorkers);

        log.info("Reassigned assignment {} from workers {} and {} to {} and {}",
                assignmentId, oldWorkerId1, oldWorkerId2, newWorkerId1, newWorkerId2);

        return "Both worker assignments updated successfully";
    }


    /**
     *
     * @param assignmentId
     * @return
     */
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