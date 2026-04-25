package ejercicio4.programacionIV.dto;

import ejercicio4.programacionIV.dto.DireccionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ejercicio4.programacionIV.validation.OnCreate;
import ejercicio4.programacionIV.validation.OnUpdate;
import ejercicio4.programacionIV.validation.DniValido;
import java.time.LocalDate;

@Getter
@Setter
public class SocioRequestDTO {

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String nombre;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String apellido;

    @NotBlank(groups = {OnCreate.class})
    @DniValido(groups = {OnCreate.class})
    private String dni;

    @Email(groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(groups = {OnCreate.class})
    private String email;

    @Size(min = 8, groups = OnCreate.class)
    @NotBlank(groups = {OnCreate.class})
    private String password;

    @Past(groups = {OnCreate.class, OnUpdate.class})
    private LocalDate fechaNacimiento;

    @Valid
    @NotNull(groups = OnCreate.class)
    private DireccionDTO direccion;
}


