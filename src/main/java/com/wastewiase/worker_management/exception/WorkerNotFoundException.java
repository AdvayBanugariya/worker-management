package com.wastewiase.worker_management.exception;

public class WorkerNotFoundException extends RuntimeException{
    public WorkerNotFoundException(String message) {
        super(message);
    }
}
