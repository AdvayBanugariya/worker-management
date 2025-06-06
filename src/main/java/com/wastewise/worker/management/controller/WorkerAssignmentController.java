package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.dto.WorkerAssignmentDTO;
import com.wastewise.worker.management.dto.WorkerReassignRequestDTO;
import com.wastewise.worker.management.service.serviceimpl.WorkerAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wastewise/admin/worker-assignments")
public class WorkerAssignmentController {

    private final WorkerAssignmentService workerAssignmentService;

    public WorkerAssignmentController(WorkerAssignmentService workerAssignmentService) {
        this.workerAssignmentService = workerAssignmentService;
    }

    @GetMapping
    public ResponseEntity<List<WorkerAssignmentDTO>> findAllWorkerAssignments(){
        return new ResponseEntity<>(workerAssignmentService.findAllWorkerAssignments(), HttpStatus.FOUND);
    }

    /**
     * Assigns worker to assignment
     * @param assignmentId assignmentId of the assignment
     * @return String indicating successful creation of tuple
     */
    @PostMapping("/{assignmentId}")
    public ResponseEntity<String> assignWorkerToAssignment(
            @PathVariable String assignmentId,
    @RequestBody WorkerAssignmentDTO dto) {
        String result = workerAssignmentService.assignWorkertoAssignment(assignmentId, dto.getWorkerId(), dto);
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
     * @param request
     * @return
     */
    @PutMapping("/reassign/{assignmentId}/")
    public ResponseEntity<String> updateBothWorkerAssignments(
            @PathVariable String assignmentId,
            @RequestBody WorkerReassignRequestDTO request) {
        return ResponseEntity.ok(
                workerAssignmentService.updateBothWorkerAssignments(
                        assignmentId,
                        request.getOldWorkerId1(),
                        request.getOldWorkerId2(),
                        request.getNewWorkerId1(),
                        request.getNewWorkerId2()));
    }

    //fetch workerIds related to the assignmentId passed

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
