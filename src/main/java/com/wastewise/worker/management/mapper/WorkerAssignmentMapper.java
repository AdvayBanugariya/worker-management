package com.wastewise.worker.management.mapper;

import com.wastewise.worker.management.dto.WorkerAssignmentDTO;
import com.wastewise.worker.management.model.WorkerAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkerAssignmentMapper {

    @Mapping(target = "worker", source = "workerId")
    WorkerAssignment toEntity(WorkerAssignmentDTO dto);

    @Mapping(target = "workerId", source = "worker.workerId")
    WorkerAssignmentDTO toDTO(WorkerAssignment entity);
}