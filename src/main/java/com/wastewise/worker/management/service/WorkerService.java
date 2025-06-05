package com.wastewise.worker.management.service;

import com.wastewise.worker.management.dto.WorkerCreateDTO;
import com.wastewise.worker.management.dto.WorkerDTO;
import com.wastewise.worker.management.dto.WorkerUpdateDTO;

import java.util.List;

public interface WorkerService {
    String createWorker(WorkerCreateDTO dto);

    List<WorkerDTO> getAllWorkers();

    WorkerDTO getWorker(String id);

    List<String> getWorkerIds();

    List<String> getAllAvailableWorkerIds();

    WorkerUpdateDTO updateWorker(String id, WorkerUpdateDTO dto);

}
