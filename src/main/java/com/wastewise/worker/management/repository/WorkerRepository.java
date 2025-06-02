package com.wastewise.worker.management.repository;

import com.wastewise.worker.management.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkerRepository extends JpaRepository<Worker,String> {

    @Query("Select count(w) from Worker w")
    Long countAll();
}
