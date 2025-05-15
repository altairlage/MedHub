package com.fiap.medsched.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SchemaMapping("CreateUpdateAppointmentRequest")
public class CreateUpdateAppointmentRequest {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String appointmentDate;
}
