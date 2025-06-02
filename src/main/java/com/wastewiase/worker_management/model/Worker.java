package com.wastewiase.worker_management.model;

import com.wastewiase.worker_management.enums.WorkerStatus;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity representing a sanitation worker in the system.
 */
@Entity
@Table(name = "worker")
public class Worker {

    @Id
    @Column(name = "worker_id")
    private String workerId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "worker_status")
    private WorkerStatus workerStatus;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Worker() {}

    public Worker(String workerId, String name, String contactNumber, String contactEmail,
                  WorkerStatus workerStatus, String roleId, String createdBy,
                  Date createdDate, String updatedBy, Date updatedDate) {
        this.workerId = workerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.workerStatus = workerStatus;
        this.roleId = roleId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    /** Getters and Setters */

    public String getWorkerId() { return workerId; }
    public void setWorkerId(String workerId) { this.workerId = workerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public WorkerStatus getWorkerStatus() { return workerStatus; }
    public void setWorkerStatus(WorkerStatus workerStatus) { this.workerStatus = workerStatus; }

    public String getRoleId() { return roleId; }
    public void setRoleId(String roleId) { this.roleId = roleId; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public Date getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}
