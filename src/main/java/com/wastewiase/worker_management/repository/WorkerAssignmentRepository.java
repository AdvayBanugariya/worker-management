package com.wastewiase.worker_management.repository;

import com.wastewiase.worker_management.model.WorkerAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerAssignmentRepository extends JpaRepository<WorkerAssignment, String> {
}