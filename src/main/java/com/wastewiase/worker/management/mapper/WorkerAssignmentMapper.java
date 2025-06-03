package com.wastewiase.worker.management.mapper;

import com.wastewiase.worker.management.dto.WorkerAssignmentDTO;
import com.wastewiase.worker.management.model.WorkerAssignment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerAssignmentMapper {

    WorkerAssignment toEntity(WorkerAssignmentDTO dto);

    WorkerAssignmentDTO toDTO(WorkerAssignment entity);
}
