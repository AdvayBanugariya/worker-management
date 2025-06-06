CREATE TABLE worker (
    worker_id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    contact_number VARCHAR(255),
    contact_email VARCHAR(255),
    worker_status ENUM('ABSENT', 'AVAILABLE', 'OCCUPIED'),
    role_id VARCHAR(255),
    created_by VARCHAR(255),
    created_date DATETIME(6),
    updated_by VARCHAR(255),
    updated_date DATETIME(6),
    PRIMARY KEY (worker_id)
) ENGINE=InnoDB;


CREATE TABLE worker_assignment (
    assignment_id VARCHAR(255) NOT NULL,
    worker_id VARCHAR(255) NOT NULL,
    zone_id VARCHAR(255),
    route_id VARCHAR(255),
    shift ENUM('DAY', 'NIGHT'),
    created_by VARCHAR(255),
    created_date DATETIME(6),
    updated_by VARCHAR(255),
    updated_date DATETIME(6),
    PRIMARY KEY (assignment_id, worker_id),
    CONSTRAINT fk_worker FOREIGN KEY (worker_id) REFERENCES worker(worker_id)
) ENGINE=InnoDB;
