INSERT INTO worker (worker_id, name, contact_number, contact_email, worker_status, role_id, created_by, created_date, updated_by, updated_date) VALUES ('W001', 'Worker_001', '555-010001', 'worker_001@example.com', 'ABSENT', 'R001', 'admin', '2020-02-14 06:10:45', 'admin', '2020-08-23 06:10:45');
INSERT INTO worker (worker_id, name, contact_number, contact_email, worker_status, role_id, created_by, created_date, updated_by, updated_date) VALUES ('W002', 'Worker_002', '555-010002', 'worker_002@example.com', 'OCCUPIED', 'R002', 'admin', '2022-05-29 09:41:14', 'admin', '2022-08-23 09:41:14');
INSERT INTO worker (worker_id, name, contact_number, contact_email, worker_status, role_id, created_by, created_date, updated_by, updated_date) VALUES ('W003', 'Worker_003', '555-010003', 'worker_003@example.com', 'OCCUPIED', 'R003', 'admin', '2021-09-24 14:00:58', 'admin', '2022-07-03 14:00:58');


INSERT INTO worker_assignment (assignment_id, worker_id, zone_id, route_id, shift, created_by, created_date, updated_by, updated_date) VALUES ('A001', 'W001', 'Z001', 'R001', 'NIGHT', 'admin', '2021-01-15 10:23:45', 'admin', '2021-07-15 10:23:45');
INSERT INTO worker_assignment (assignment_id, worker_id, zone_id, route_id, shift, created_by, created_date, updated_by, updated_date) VALUES ('A002', 'W002', 'Z002', 'R002', 'DAY', 'admin', '2022-03-10 08:15:30', 'admin', '2022-09-10 08:15:30');

