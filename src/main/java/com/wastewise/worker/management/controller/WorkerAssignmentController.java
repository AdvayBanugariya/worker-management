package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.exception.ResourceNotFoundException;
import com.wastewise.worker.management.service.WorkerAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
    @DeleteMapping
    public ResponseEntity<String> deleteWorkerAssignment(
            @RequestParam String assignmentId,
            @RequestParam String workerId) {
        String result = workerAssignmentService.deleteWorkerAssignment(assignmentId, workerId);
        return ResponseEntity.ok(result);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        String errorMessage = String.format("Error: %s (Status: %d)", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


}
