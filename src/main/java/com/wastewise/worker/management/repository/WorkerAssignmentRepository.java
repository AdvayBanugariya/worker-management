package com.wastewise.worker.management.repository;

import com.wastewise.worker.management.model.WorkerAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerAssignmentRepository extends JpaRepository<WorkerAssignment, String> {
}
