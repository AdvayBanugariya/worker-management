package com.wastewise.worker.management.dto;

import com.wastewise.worker.management.enums.Shift;
import com.wastewise.worker.management.utility.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class WorkerAssignmentDTO {
    @NotBlank(message = "assignmentId should not be blank")
    @Pattern(regexp = "^A//d{3}$", message = "The assignmentId should follow this pattern 'A001'")
    private String assignmentId;

    @NotBlank(message = "workerId should not be blank")
    @Pattern(regexp = "^W//d{3}$", message = "WorkerId should follow this pattern 'W001'")
    private String workerId;

    @NotBlank(message = "zoneId should not be blank")
    @Pattern(regexp = "^Z//d{3}$", message = "ZoneId should follow this pattern 'Z001'")
    private String zoneId;

    @NotBlank(message = "routeId should not be blank")
    @Pattern(regexp = "^Z//d{3}-R//d{3}$", message = "WorkerId should follow this pattern 'W001'")
    private String routeId;

    @NotBlank(message = "Shift cannot be blank")
    @ValidEnum(enumClass = Shift.class, message = "Shift details should be either 'DAY' or 'NIGHT'")
    private Shift shift;
}