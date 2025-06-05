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
     *
     * @param assignmentId
     * @param oldWorkerId
     * @param newWorkerId
     * @return
     */
    @PutMapping("/update/{assignmentId}/{oldWorkerId}/{newWorkerId}")
    public ResponseEntity<String> updateWorkerAssignment(
            @PathVariable String assignmentId,
            @PathVariable String oldWorkerId,
            @PathVariable String newWorkerId) {
        String result = workerAssignmentService.updateSingleWorkerAssignment(assignmentId, oldWorkerId, newWorkerId);
        return ResponseEntity.ok(result);
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
    @PutMapping("/update/{assignmentId}/{oldWorkerId1}/{oldWorkerId2}/{newWorkerId1}/{newWorkerId2}")
    public ResponseEntity<String> updateBothWorkerAssignments(
            @PathVariable String assignmentId,
            @PathVariable String oldWorkerId1,
            @PathVariable String oldWorkerId2,
            @PathVariable String newWorkerId1,
            @PathVariable String newWorkerId2) {

        String result = workerAssignmentService.updateBothWorkerAssignments(
                assignmentId, oldWorkerId1, oldWorkerId2, newWorkerId1, newWorkerId2
        );
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
