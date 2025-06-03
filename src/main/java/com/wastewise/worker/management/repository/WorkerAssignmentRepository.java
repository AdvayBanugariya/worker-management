package com.wastewise.worker.management.repository;

import com.wastewise.worker.management.model.WorkerAssignment;
import com.wastewise.worker.management.model.WorkerAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerAssignmentRepository extends JpaRepository<WorkerAssignment, WorkerAssignmentId> {
}
