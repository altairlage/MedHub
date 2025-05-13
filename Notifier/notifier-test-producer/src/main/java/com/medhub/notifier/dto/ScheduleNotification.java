package com.medhub.notifier.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.medhub.notifier.enums.EventTypeEnum;

import java.time.LocalDateTime;

public record ScheduleNotification(
        @JsonProperty("id") Long id,
        @JsonProperty("tipoDeEvento") EventTypeEnum tipoDeEvento,
        @JsonProperty("nomePaciente") String nomePaciente,
        @JsonProperty("emailPaciente") String emailPaciente,
        @JsonProperty("nomeMedico") String nomeMedico,
        @JsonProperty("dataHora")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        LocalDateTime dataHora
) {}
