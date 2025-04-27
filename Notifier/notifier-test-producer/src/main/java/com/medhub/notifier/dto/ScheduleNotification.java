package com.medhub.notifier.dto;

import java.time.LocalDateTime;

public record ScheduleNotification(
        Long id,
        String tipoDeEvento, // criação, update ou delete de agendamento
        String NomePaciente,
        String NomeMedico,
        LocalDateTime dataHora
) { }
