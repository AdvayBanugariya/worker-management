package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.dto.WorkerAssignmentDTO;
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
     * Assigns worker to assignment
     * @param assignmentId assignmentId of the assignment
     * @param workerId workerId of the available worker
     * @return String indicating successful creation of tuple
     */
    @PostMapping("/{assignmentId}/{workerId}")
    public ResponseEntity<String> assignWorkerToAssignment(
            @PathVariable String assignmentId,
            @PathVariable String workerId,
    @RequestBody WorkerAssignmentDTO dto) {
        String result = workerAssignmentService.assignWorkertoAssignment(assignmentId, workerId, dto);
        return ResponseEntity.ok(result);
    }



    /**
     *
     * @param assignmentId of the assignment to which new worker needs to he assigned
     * @param oldWorkerId of the worker who is already assigned to the task
     * @param newWorkerId of the worker who is going to replace the old worker
     * @return String stating that the update has been executed
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
     * Deletes assignment and updates the status of the workers
     * @param assignmentId id of assignment related to which we need to delete the tuples of worker assignment
     * @return string stating that the worker assignment is deleted
     */
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<String> deleteWorkerAssignment(
            @PathVariable String assignmentId) {
        String result = workerAssignmentService.deleteWorkerAssignment(assignmentId);
        return ResponseEntity.ok(result);
    }


}
