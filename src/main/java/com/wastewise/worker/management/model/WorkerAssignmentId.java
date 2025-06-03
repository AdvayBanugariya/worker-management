package com.wastewise.worker.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key for WorkerAssignment consisting of assignmentId and workerId.
 */
@Embeddable
public class WorkerAssignmentId implements Serializable {

    @Column(name = "assignment_id")
    private String assignmentId;

    @Column(name = "worker_id")
    private String workerId;

    public WorkerAssignmentId() {}

    public WorkerAssignmentId(String assignmentId, String workerId) {
        this.assignmentId = assignmentId;
        this.workerId = workerId;
    }

    public String getAssignmentId() { return assignmentId; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }

    public String getWorkerId() { return workerId; }
    public void setWorkerId(String workerId) { this.workerId = workerId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkerAssignmentId)) return false;
        WorkerAssignmentId that = (WorkerAssignmentId) o;
        return Objects.equals(assignmentId, that.assignmentId) &&
                Objects.equals(workerId, that.workerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, workerId);
    }
}