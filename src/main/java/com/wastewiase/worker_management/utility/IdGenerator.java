package com.wastewiase.worker_management.utility;

import com.wastewiase.worker_management.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    @Autowired
    private WorkerRepository workerRepository;

    public String generateWorkerId() {
        long count = workerRepository.countAll();
        return String.format("%04d", count + 1);
    }
}