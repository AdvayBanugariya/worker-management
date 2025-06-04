package com.wastewise.worker.management.controller;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.dto.WorkerDTO;
import com.wastewise.worker.management.dto.WorkerUpdateDTO;
import com.wastewise.worker.management.exception.WorkerNotFoundException;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wastewise/admin/dashboard/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public ResponseEntity<Worker> createWorker(@RequestBody WorkerCreateDTO dto) {
        log.info("Creating a new worker profile");
        Worker createdWorker = workerService.createWorker(dto);
        return ResponseEntity.ok(createdWorker);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> getWorker(@PathVariable String id) {
        WorkerDTO worker = workerService.getWorker(id); // Exception handled globally
        return ResponseEntity.ok(worker);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<String>> getAllWorkerIds() {
        return ResponseEntity.ok(workerService.getWorkerIds());
    }

    @GetMapping("/ids/available")
    public ResponseEntity<List<String>> getAvailableWorkerIds() {
        return ResponseEntity.ok(workerService.getAllAvailableWorkerIds());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkerUpdateDTO> updateWorker(@PathVariable String id,
                                                        @RequestBody WorkerUpdateDTO dto) {
        WorkerUpdateDTO updated = workerService.updateWorker(id, dto); // Exception handled globally
        return ResponseEntity.ok(updated);
    }
}
