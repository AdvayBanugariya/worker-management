package com.wastewise.worker.management.service;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.model.Worker;
import com.wastewise.worker.management.repository.WorkerRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository){
        this.workerRepository = workerRepository;
    }

    public Worker createWorker(WorkerCreateDTO worker){

    }
}
