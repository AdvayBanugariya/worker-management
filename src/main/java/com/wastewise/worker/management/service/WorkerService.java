package com.wastewise.worker.management.service;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.dto.WorkerDTO;
import com.wastewise.worker.management.dto.WorkerUpdateDTO;
import com.wastewise.worker.management.exception.WorkerNotFoundException;
import com.wastewise.worker.management.mapper.WorkerMapper;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.repository.WorkerRepository;
import com.wastewise.worker.management.utility.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
     * Creates a new worker in the system, takes dto of workerCreateDTO
     * @param dto
     * @returns worker
     */
    public Worker createWorker(WorkerCreateDTO dto) {
        log.info("Creating new worker: {}", idGenerator.generateWorkerId());
        Worker worker = workerMapper.toEntity(dto);
        worker.setWorkerId(idGenerator.generateWorkerId());
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
     *
     * @returns list of all worker Ids
     */
    public List<String> getWorkerIds() {
        return workerRepository.findAllWorkerId();
    }

    /**
     * Retrieves all available worker IDs (based on status).
     *
     * @returns list of all workers with status = "Available"
     */
    public List<String> getAllAvailableWorkerIds() {
        return workerRepository.findWorkerIdAvailableStatus();
    }

    /**
     * Updates an existing worker's information.
     *
     * @returns dto of WorkerUpdateDTO
     */
    public WorkerUpdateDTO updateWorker(String id, WorkerUpdateDTO dto) throws WorkerNotFoundException {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " does not exist"));
        workerMapper.updateWorkerFromDTO(dto, worker);
        worker.setUpdatedDate(new Date());
        return dto;
    }
}