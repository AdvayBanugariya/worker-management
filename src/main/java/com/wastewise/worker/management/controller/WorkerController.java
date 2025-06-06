package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.dto.WorkerDTO;
import com.wastewise.worker.management.dto.WorkerUpdateDTO;
import com.wastewise.worker.management.enums.WorkerStatus;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.service.serviceimpl.WorkerService;
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

    @PostMapping
    public ResponseEntity<String> createWorker(@RequestBody WorkerCreateDTO dto) {
        log.info("Creating a new worker profile");
        return ResponseEntity.ok(workerService.createWorker(dto));
    }

    @GetMapping
    public ResponseEntity<List<WorkerDTO>> findAllWorkers(){
        log.info("Fetching all the workers");
        return ResponseEntity.ok(workerService.getAllWorkers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> getWorker(@PathVariable String id) {
        WorkerDTO worker = workerService.getWorker(id); // Exception handled globally
        return ResponseEntity.ok(worker);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<String>> getAllWorkerIds() {
        return new ResponseEntity<>(workerService.getWorkerIds(), HttpStatus.OK);
    }

    @GetMapping("/ids/available")
    public ResponseEntity<List<String>> getAvailableWorkerIds() {
        return ResponseEntity.ok(workerService.getAllAvailableWorkerIds());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorker(@PathVariable String id,
                                                        @RequestBody WorkerUpdateDTO dto) {
        return ResponseEntity.ok(workerService.updateWorker(id, dto));
    }

    @PutMapping("/status/{workerId}")
    public ResponseEntity<String> updatWorkerStatus(@PathVariable String workerId, @RequestBody WorkerStatus workerStatus){
        return ResponseEntity.ok(workerService.changeWorkerStatus(workerId, workerStatus));
    }
}
