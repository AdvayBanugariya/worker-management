package com.wastewiase.worker.management.model;

import com.wastewiase.worker.management.enums.Shift;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity representing assignment of a worker to a zone/route for a shift.
 */
@Entity
@Table(name = "worker_assignment")
public class WorkerAssignment {

    @EmbeddedId
    private WorkerAssignmentId id;

    @OneToOne
    @MapsId("workerId")  // maps the embedded FK
    @JoinColumn(name = "worker_id", referencedColumnName = "worker_id")
    private Worker worker;

    @Column(name = "zone_id")
    private String zoneId;

    @Column(name = "route_id")
    private String routeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "shift")
    private Shift shift;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    public WorkerAssignment() {}

    public WorkerAssignment(WorkerAssignmentId id, Worker worker, String zoneId, String routeId,
                            Shift shift, String createdBy, Date createdDate,
                            String updatedBy, Date updatedDate) {
        this.id = id;
        this.worker = worker;
        this.zoneId = zoneId;
        this.routeId = routeId;
        this.shift = shift;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    /** Getters and Setters */

    public WorkerAssignmentId getId() { return id; }
    public void setId(WorkerAssignmentId id) { this.id = id; }

    public Worker getWorker() { return worker; }
    public void setWorker(Worker worker) { this.worker = worker; }

    public String getZoneId() { return zoneId; }
    public void setZoneId(String zoneId) { this.zoneId = zoneId; }

    public String getRouteId() { return routeId; }
    public void setRouteId(String routeId) { this.routeId = routeId; }

    public Shift getShift() { return shift; }
    public void setShift(Shift shift) { this.shift = shift; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public Date getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}
