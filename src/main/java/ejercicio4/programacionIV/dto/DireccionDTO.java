package ejercicio4.programacionIV.dto;

import ejercicio4.programacionIV.validation.OnCreate;
import ejercicio4.programacionIV.validation.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DireccionDTO {

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 5, max = 150, groups = {OnCreate.class, OnUpdate.class})
    private String calle;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 80, groups = {OnCreate.class, OnUpdate.class})
    private String ciudad;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String provincia;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Pattern(regexp = "\\d{4}", groups = {OnCreate.class, OnUpdate.class})
    private String codigoPostal;
}