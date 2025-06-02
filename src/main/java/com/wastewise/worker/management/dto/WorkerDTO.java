package com.wastewise.worker.management.dto;

import com.wastewise.worker.management.enums.WorkerStatus;

import java.util.Date;

public class WorkerDTO {
    private String id;
    private String name;
    private String contactNumber;
    private String contactEmail;
    private WorkerStatus workerStatus;
    private String role_id;

    public WorkerDTO(){}

    public WorkerDTO(String id, String name,
                     String contactNumber,
                     String contactEmail, WorkerStatus workerStatus,
                     String role_id) {
        super();
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.workerStatus = workerStatus;
        this.role_id = role_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
