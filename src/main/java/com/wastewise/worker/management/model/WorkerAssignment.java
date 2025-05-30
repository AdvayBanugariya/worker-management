package com.wastewise.worker.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "worker_assignment")
public class WorkerAssignment {
    @Id
    @Column(name = "assignment_id")
    private String assignmentId;

    @Id
    @Column(name = "worker_id")
    private Worker worker;


}
