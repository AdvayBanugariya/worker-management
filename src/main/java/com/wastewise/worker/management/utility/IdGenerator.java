package com.wastewise.worker.management.utility;

import com.wastewise.worker.management.repository.WorkerRepository;
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
