package com.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    @NotBlank(message = "El titulo no puede estar en blanco")
    @Size(min = 2, max = 255, message = "El titulo debe tener entre 2 y 255 caracteres")
    private String title;
    @NotBlank(message = "La descripcion no puede estar en blanco")
    @Size(min = 10, message = "La descripcion debe tener al menos 10 caracteres")
    private String description;
    @URL(message = "La URL de la imagen no tiene un formato valido")
    private String imageUrl;
    @URL(message = "La URL del proyecto no tiene un formato valido")
    private String projectUrl;
    @NotNull(message = "El ID de informacion personal no puede ser nulo")
    @Min(value = 1, message = "El ID de informacion personal debe ser un numero positivo")
    private Long personalInfoId;
}
