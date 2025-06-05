package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.service.serviceimpl.WorkerAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wastewise/admin?dashboard/worker-assignments")
public class WorkerAssignmentController {

    private final WorkerAssignmentService workerAssignmentService;

    public WorkerAssignmentController(WorkerAssignmentService workerAssignmentService) {
        this.workerAssignmentService = workerAssignmentService;
    }

    /**
     * Deletes a worker assignment by assignmentId and workerId.
     */
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<String> deleteWorkerAssignment(
            @PathVariable String assignmentId) {
        String result = workerAssignmentService.deleteWorkerAssignment(assignmentId);
        return ResponseEntity.ok(result);
    }



}
