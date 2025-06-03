package com.wastewiase.worker.management.mapper;

import com.wastewiase.worker.management.dto.WorkerCreateDTO;
import com.wastewiase.worker.management.dto.WorkerDTO;
import com.wastewiase.worker.management.dto.WorkerUpdateDTO;
import com.wastewiase.worker.management.model.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

    Worker toEntity(WorkerCreateDTO dto);

    WorkerDTO toDTO(Worker entity);

    @Mapping(target = "workerId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void updateWorkerFromDTO(WorkerUpdateDTO dto, @MappingTarget Worker entity);
}
