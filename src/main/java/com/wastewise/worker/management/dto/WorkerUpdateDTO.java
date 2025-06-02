package com.wastewise.worker.management.dto;

import com.wastewise.worker.management.enums.WorkerStatus;

import java.util.Date;

public class WorkerUpdateDTO {
    private String name;
    private String contactNumber;
    private String contactEmail;
    private WorkerStatus workerStatus;
    private String role_id;
    private String updatedBy;
    private Date updatedDate;

    public WorkerUpdateDTO(){}

    public WorkerUpdateDTO(String name, String contactNumber,
                           String contactEmail, WorkerStatus workerStatus,
                           String role_id, String updatedBy,
                           Date updatedDate) {
        super();
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.workerStatus = workerStatus;
        this.role_id = role_id;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
