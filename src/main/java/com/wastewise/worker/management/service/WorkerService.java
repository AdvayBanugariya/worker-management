package com.wastewise.worker.management.service;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.dto.WorkerDTO;
import com.wastewise.worker.management.dto.WorkerUpdateDTO;
import com.wastewise.worker.management.exception.WorkerNotFoundException;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.repository.WorkerRepository;
import com.wastewise.worker.management.utility.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    private IdGenerator idGenerator;

    public WorkerService(WorkerRepository workerRepository){
        this.workerRepository = workerRepository;
    }

    public Worker createWorker(WorkerCreateDTO worker){
        Worker worker1 = new Worker();
        worker1.setWorkerId(idGenerator.generateWorkerId());
        worker1.setName(worker.getName());
        worker1.setWorkerStatus(worker.getWorkerStatus());
        worker1.setContactNumber(worker.getContactNumber());
        worker1.setContactEmail(worker.getContactEmail());
        worker1.setCreatedBy(worker.getCreatedBy());
        worker1.setCreatedDate(new Date());

        return workerRepository.save(worker1);
    }

    public WorkerDTO getWorker(String id) throws WorkerNotFoundException {
        Optional<Worker> workerOptional =  workerRepository.findById(id);
        if(workerOptional.isEmpty()){
            throw new WorkerNotFoundException("Worker with id "+ id + " does not exist");
        }
        Worker worker = workerOptional.get();
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(worker.getWorkerId());
        workerDTO.setName(worker.getName());
        workerDTO.setWorkerStatus(worker.getWorkerStatus());
        workerDTO.setContactEmail(worker.getContactEmail());
        workerDTO.setContactNumber(worker.getContactNumber());
        workerDTO.setWorkerStatus(worker.getWorkerStatus());

        return workerDTO;
    }

    public List<String> getWorkerIds(){
        return workerRepository.findAllWorkerId();
    }

    public List<String> getAllAvailableWorkerIds(){
        return workerRepository.findWorkerIdAvailableStatus();
    }
}
