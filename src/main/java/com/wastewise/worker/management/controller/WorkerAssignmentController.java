package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.dto.UpdateWorkerAssignDTO;
import com.wastewise.worker.management.dto.WorkerAssignmentDTO;
import com.wastewise.worker.management.dto.WorkerReassignRequestDTO;
import com.wastewise.worker.management.service.serviceimpl.WorkerAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wastewise/admin/worker-assignments")
public class WorkerAssignmentController {

    private final WorkerAssignmentService workerAssignmentService;

    public WorkerAssignmentController(WorkerAssignmentService workerAssignmentService) {
        this.workerAssignmentService = workerAssignmentService;
    }

    /**
     * Finding all the worker-assignments from the database
     * @return list of workerAssignmentDTO(assignmentId, workerId, routeId, zoneId, shift)
     */
    @GetMapping
    public ResponseEntity<List<WorkerAssignmentDTO>> findAllWorkerAssignments(){
        log.info("fetching all the workerAssignments");
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
        log.info("assigning worker");
        String result = workerAssignmentService.assignWorkertoAssignment(assignmentId, dto.getWorkerId(), dto);
        return ResponseEntity.ok(result);
    }



    /**
     *
     * @param assignmentId of the assignment to which new worker needs to he assigned
     * @param dto updateWorkerAssignDTO (OldWorkerId, newWorkerId)
     * @return String stating that the update has been executed
     */
    @PutMapping("/update/{assignmentId}")
    public ResponseEntity<String> updateWorkerAssignment(
            @PathVariable String assignmentId,
            @RequestBody UpdateWorkerAssignDTO dto) {
        String result = workerAssignmentService.updateSingleWorkerAssignment(assignmentId, dto.getOldWorkerId(), dto.getNewWorkerId());
        return ResponseEntity.ok(result);
    }

    /**
     * update both the workers assigned to the assignment with new workers
     * @param assignmentId id of the assignment
     * @param request WorkerReassignDTO(oldWorkerId1, oldWorkerId2, newWorkerId1, newWorkerId2)
     * @return String message explaining the result
     */
    @PutMapping("/reassign/{assignmentId}/")
    public ResponseEntity<String> updateBothWorkerAssignments(
            @PathVariable String assignmentId,
            @RequestBody WorkerReassignRequestDTO request) {
        log.info("Replacing workers {} and {} assigned to the assignment {} with workers {} and {}",
                request.getOldWorkerId1(),request.getOldWorkerId1(),
                assignmentId,request.getNewWorkerId1(),request.getNewWorkerId2());
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
        log.info("Deleting workerAssignments with assignmentId {} and chaning the status of assigned workers", assignmentId);
        String result = workerAssignmentService.deleteWorkerAssignment(assignmentId);
        return ResponseEntity.ok(result);
    }


}
