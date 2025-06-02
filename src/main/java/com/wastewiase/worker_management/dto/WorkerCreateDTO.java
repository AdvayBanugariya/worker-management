package com.wastewiase.worker_management.dto;

import com.wastewiase.worker_management.enums.WorkerStatus;

import java.util.Date;

public class WorkerCreateDTO {

    private String name;
    private String contactNumber;
    private String contactEmail;
    private WorkerStatus workerStatus;
    private String role_id;
    private String createdBy;
    private Date createdDate;

    public WorkerCreateDTO(){}

    public WorkerCreateDTO(String name, String contactNumber,
                           String contactEmail, WorkerStatus workerStatus,
                           String role_id, String createdBy,
                           Date createdDate) {
        super();
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.workerStatus = workerStatus;
        this.role_id = role_id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public WorkerStatus getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(WorkerStatus workerStatus) {
        this.workerStatus = workerStatus;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}