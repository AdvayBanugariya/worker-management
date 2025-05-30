package com.wastewise.worker.management.model;

import com.wastewise.worker.management.enums.WorkerStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Worker {
    @Id
    @Column(name = "worker_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "worker_status")
    @Enumerated(EnumType.STRING)
    private WorkerStatus workerStatus;

    @Column(name = "role_id")
    private String role_id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;
}
