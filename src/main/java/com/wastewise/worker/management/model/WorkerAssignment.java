package com.wastewise.worker.management.model;

import com.wastewise.worker.management.enums.Shift;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "worker_assignment")
public class WorkerAssignment {
    @Id
    @Column(name = "assignment_id")
    private String assignmentId;

    @Id
    @OneToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "worker_id", insertable = false, updatable = false)
    private Worker worker;

    @Column(name = "zone_id")
    private String zoneId;

    @Column(name = "route_id")
    private String routeId;

    @Column(name = "shift")
    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    public WorkerAssignment(){}

    public WorkerAssignment(String assignmentId, Worker worker,
                            String zoneId, String routeId,
                            Shift shift, String createdBy,
                            Date createdDate, String updatedBy,
                            Date updatedDate) {
        super();
        this.assignmentId = assignmentId;
        this.worker = worker;
        this.zoneId = zoneId;
        this.routeId = routeId;
        this.shift = shift;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
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
