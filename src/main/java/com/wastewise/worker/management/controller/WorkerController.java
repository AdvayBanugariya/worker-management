package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.dto.WorkerDTO;
import com.wastewise.worker.management.dto.WorkerUpdateDTO;
import com.wastewise.worker.management.enums.WorkerStatus;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.service.serviceimpl.WorkerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wastewise/admin/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    /**
     * Creating a new worker
     * @param dto takes dto of worker as input. (name, contactNumber, contactEmail, roleId, Status)
     * @return string message for confirmation of successful or failed execution
     */
    @PostMapping
    public ResponseEntity<String> createWorker(@Valid @RequestBody WorkerCreateDTO dto) {
        log.info("Creating a new worker profile");
        return ResponseEntity.ok(workerService.createWorker(dto));
    }

    /**
     * finding all the workers
     * @return a list of workerDTO (workerId, name, contactNumber, contactEmail, roleId, Status
     */
    @GetMapping
    public ResponseEntity<List<WorkerDTO>> findAllWorkers(){
        log.info("Fetching all the workers");
        return ResponseEntity.ok(workerService.getAllWorkers());
    }

    /**
     * getting worker by workerId
     * @param id workerId is passed as a prameter
     * @return workerDTO object
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> getWorker(@PathVariable String id) {
        log.info("Finding worker with id {}",id);
        WorkerDTO worker = workerService.getWorker(id);
        return ResponseEntity.ok(worker);
    }

    /**
     * getting all the workerIds irrespective of role and status
     * @return List of all the workerIds
     */
    @GetMapping("/ids")
    public ResponseEntity<List<String>> getAllWorkerIds() {
        log.info("fetching the list of all workerIds");
        return new ResponseEntity<>(workerService.getWorkerIds(), HttpStatus.OK);
    }

    /**
     * finding all the workers with status Available
     * @return list of workerIds with status is available
     */
    @GetMapping("/ids/available")
    public ResponseEntity<List<String>> getAvailableWorkerIds() {
        log.info("fetching all the available workers");
        return ResponseEntity.ok(workerService.getAllAvailableWorkerIds());
    }

    /**
     * Updating worker information
     * @param id WorkerId is passed as a parameter to pass the object
     * @param dto WorkerUpdateDTO is passed (name, contactNumber, contactEmail, roleId, status)
     * @return String message sharing the execution status of the
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorker(@PathVariable String id,
                                                        @Valid @RequestBody WorkerUpdateDTO dto) {
        log.info("updating worker with id {}", id);
        return ResponseEntity.ok(workerService.updateWorker(id, dto));
    }

    /**
     * updating worker status
     * @param workerId workerId is passed to find the worker
     * @param workerStatus worker status is passed as request body
     * @return string message confirming the updating of status
     */
    @PutMapping("/status/{workerId}")
    public ResponseEntity<String> updatWorkerStatus(@PathVariable String workerId, @RequestBody WorkerStatus workerStatus){
        log.info("Updating the status of worker with id {} to status {}", workerId, workerStatus);
        return ResponseEntity.ok(workerService.changeWorkerStatus(workerId, workerStatus));
    }
}
