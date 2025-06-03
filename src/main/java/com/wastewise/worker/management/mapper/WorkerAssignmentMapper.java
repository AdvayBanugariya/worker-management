package com.wastewise.worker.management.mapper;

import com.wastewise.worker.management.dto.WorkerAssignmentDTO;
import com.wastewise.worker.management.model.WorkerAssignment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerAssignmentMapper {

    WorkerAssignment toEntity(WorkerAssignmentDTO dto);

    WorkerAssignmentDTO toDTO(WorkerAssignment entity);
}