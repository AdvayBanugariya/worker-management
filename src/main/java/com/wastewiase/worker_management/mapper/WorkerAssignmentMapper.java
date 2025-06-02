package com.wastewiase.worker_management.mapper;

import com.wastewiase.worker_management.dto.WorkerAssignmentDTO;
import com.wastewiase.worker_management.model.WorkerAssignment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerAssignmentMapper {

    WorkerAssignment toEntity(WorkerAssignmentDTO dto);

    WorkerAssignmentDTO toDTO(WorkerAssignment entity);
}
