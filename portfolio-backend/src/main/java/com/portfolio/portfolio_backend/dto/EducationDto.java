package com.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDto {
    private Long id;
    @NotBlank(message = "El nombre del titulo no puede estar vacio")
    private String degree;
    @NotBlank(message = "El nombre de la institucion no puede estar vacio")
    private String institution;
    @NotNull(message = "La fecha de incio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @PastOrPresent(message = "La fecha de fin no puede ser futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description;
    // validacion a nivel de servicio
    private Long personalInfoId;
}
