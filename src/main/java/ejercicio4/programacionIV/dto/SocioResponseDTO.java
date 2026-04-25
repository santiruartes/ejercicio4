package ejercicio4.programacionIV.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SocioResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDateTime fechaRegistro;
    private String estadoMembresia;
    private DireccionDTO direccion;
}
