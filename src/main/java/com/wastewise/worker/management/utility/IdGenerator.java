package com.wastewise.worker.management.utility;

import com.wastewise.worker.management.repository.WorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class IdGenerator {

    private WorkerRepository workerRepository;

    public String generateWorkerId() {
        long count = workerRepository.countAll();
        return String.format("W%03d", count + 1);
    }
}
