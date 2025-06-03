package com.wastewiase.worker.management.service;

import com.wastewiase.worker.management.dto.WorkerCreateDTO;
import com.wastewiase.worker.management.dto.WorkerDTO;
import com.wastewiase.worker.management.dto.WorkerUpdateDTO;
import com.wastewiase.worker.management.exception.WorkerNotFoundException;
import com.wastewiase.worker.management.mapper.WorkerMapper;
import com.wastewiase.worker.management.model.Worker;
import com.wastewiase.worker.management.repository.WorkerRepository;
import com.wastewiase.worker.management.utility.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;
    private final IdGenerator idGenerator;

    public WorkerService(WorkerRepository workerRepository,
                         WorkerMapper workerMapper,
                         IdGenerator idGenerator) {
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
        this.idGenerator = idGenerator;
    }

    /**
     * Creates a new worker in the system.
     */
    public Worker createWorker(WorkerCreateDTO dto) {
        String id = idGenerator.generateWorkerId();
        log.info("Creating new worker: {}", id);
        Worker worker = workerMapper.toEntity(dto);
        worker.setWorkerId(id);
        worker.setCreatedDate(new Date());
        return workerRepository.save(worker);
    }

    /**
     * Retrieves a worker by ID.
     */
    public WorkerDTO getWorker(String id) throws WorkerNotFoundException {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " does not exist"));
        return workerMapper.toDTO(worker);
    }

    /**
     * Retrieves all worker IDs.
     */
    public List<String> getWorkerIds() {
        log.info("Retrieving list of all workers");
        return workerRepository.findAllWorkerId();
    }

    /**
     * Retrieves all available worker IDs (based on status).
     */
    public List<String> getAllAvailableWorkerIds() {
        log.info("Retrieving all the worker ids with status = AVAILABLE");
        return workerRepository.findWorkerIdAvailableStatus();
    }

    /**
     * Updates an existing worker's information.
     */
    public WorkerUpdateDTO updateWorker(String id, WorkerUpdateDTO dto) throws WorkerNotFoundException {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " does not exist"));
        workerMapper.updateWorkerFromDTO(dto, worker);
        worker.setUpdatedDate(new Date());
        return dto;
    }
}
