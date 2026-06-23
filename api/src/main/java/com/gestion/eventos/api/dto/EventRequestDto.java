package com.gestion.eventos.api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventRequestDto {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate date;
    
    @NotBlank(message = "La ubicacion no puede estar vacia")
    private String location;
}
