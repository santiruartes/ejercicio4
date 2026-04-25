package ejercicio4.programacionIV.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String password;

    private LocalDate fechaNacimiento;

    private LocalDateTime fechaRegistro;

    private LocalDate fechaVencimientoMembresia;

    private String calle;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
}