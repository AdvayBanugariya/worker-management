package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.service.serviceimpl.WorkerAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wastewise/admin/worker-assignments")
public class WorkerAssignmentController {

    private final WorkerAssignmentService workerAssignmentService;

    public WorkerAssignmentController(WorkerAssignmentService workerAssignmentService) {
        this.workerAssignmentService = workerAssignmentService;
    }

    /**
     * Deletes assignment and updates the status of the workers
     * @param assignmentId
     * @returns
     */
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<String> deleteWorkerAssignment(
            @PathVariable String assignmentId) {
        String result = workerAssignmentService.deleteWorkerAssignment(assignmentId);
        return ResponseEntity.ok(result);
    }

    /**
     * updates worker assignment by changing the worker assigned to that assignment
     * @param assignmentId
     * @param newWorkerId
     * @return
     */
    @PutMapping("/update/{assignmentId}/worker/{newWorkerId}")
    public ResponseEntity<String> updateWorkerAssignment(
            @PathVariable String assignmentId,
            @PathVariable String newWorkerId) {
        String result = workerAssignmentService.updateWorkerAssignment(assignmentId, newWorkerId);
        return ResponseEntity.ok(result);
    }


    /**
     * Assigns worker to assignment
     * @param assignmentId
     * @param workerId
     * @return
     */
    @PostMapping("/assign/{assignmentId}/{workerId}")
    public ResponseEntity<String> assignWorkerToAssignment(
            @PathVariable String assignmentId,
            @PathVariable String workerId) {
        String result = workerAssignmentService.assignWorkertoAssignment(assignmentId, workerId);
        return ResponseEntity.ok(result);
    }

}
