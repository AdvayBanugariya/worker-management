package com.wastewise.worker.management.service.serviceimpl;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.dto.WorkerDTO;
import com.wastewise.worker.management.dto.WorkerUpdateDTO;
import com.wastewise.worker.management.enums.WorkerStatus;
import com.wastewise.worker.management.exception.WorkerNotFoundException;
import com.wastewise.worker.management.mapper.WorkerMapper;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.repository.WorkerRepository;
import com.wastewise.worker.management.utility.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class WorkerService implements com.wastewise.worker.management.service.WorkerService {

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
     *
     * @param dto of workerCreateDTO(name, contactNumber, contactEmail, roleId, Status)
     * @return worker creation message
     */
    @Transactional
    public String createWorker(WorkerCreateDTO dto) {
        String id = idGenerator.generateWorkerId();

        log.info("Creating new worker: {}", id);

        Worker worker = workerMapper.toEntity(dto);
        worker.setWorkerId(id);
        worker.setCreatedDate(LocalDateTime.now());
        workerRepository.save(worker);
        return "Created worker with id " + worker.getWorkerId();
    }

    public List<WorkerDTO> getAllWorkers(){
        return workerRepository.findAll()
                .stream()
                .map(workerMapper::toDTO)
                .toList();
    }

    /**
     * Retrieves a worker by ID.
     *
     * @return workerDTO object that is consist of worker id, name, contact details, status and roleId
     */
    @Transactional
    public WorkerDTO getWorker(String id) throws WorkerNotFoundException {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " does not exist"));
        return workerMapper.toDTO(worker);
    }

    /**
     * Retrieves all worker IDs.
     *
     * @return list of all worker Ids
     */
    public List<String> getWorkerIds() {
        return workerRepository.findAllWorkerId();
    }

    /**
     * Retrieves all available worker IDs (based on status).
     *
     * @return list of all workers with status = "Available"
     */
    public List<String> getAllAvailableWorkerIds() {
        return workerRepository.findWorkerIdAvailableStatus();
    }

    /**
     * Updates an existing worker's information.
     *
     * @return dto of WorkerUpdateDTO
     */
    @Transactional
    public String updateWorker(String id, WorkerUpdateDTO dto) throws WorkerNotFoundException {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " does not exist"));
        workerMapper.updateWorkerFromDTO(dto, worker);
        worker.setUpdatedDate(LocalDateTime.now());
        workerRepository.save(worker);
        return "Updated worker with id "+worker.getWorkerId();
    }

    @Transactional
    public String changeWorkerStatus(String id, WorkerStatus workerStatus){
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " does not exist"));
        worker.setWorkerStatus(workerStatus);
        workerRepository.save(worker);

        return "Status of worker with id "+ id + " changes successfully";
    }
}