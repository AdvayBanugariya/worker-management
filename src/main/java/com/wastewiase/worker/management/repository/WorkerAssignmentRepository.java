package com.wastewiase.worker.management.repository;

import com.wastewiase.worker.management.model.WorkerAssignment;
import com.wastewiase.worker.management.model.WorkerAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerAssignmentRepository extends JpaRepository<WorkerAssignment, WorkerAssignmentId> {
}