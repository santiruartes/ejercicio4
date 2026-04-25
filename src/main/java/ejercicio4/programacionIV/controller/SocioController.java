package ejercicio4.programacionIV.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ejercicio4.programacionIV.dto.SocioResponseDTO;
import ejercicio4.programacionIV.service.SocioService;
import ejercicio4.programacionIV.dto.SocioRequestDTO;
import ejercicio4.programacionIV.validation.OnCreate;
import ejercicio4.programacionIV.validation.OnUpdate;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @PostMapping
    public ResponseEntity<SocioResponseDTO> crear(
            @RequestBody @Validated(OnCreate.class) SocioRequestDTO dto
    ) {
        SocioResponseDTO socioCreado = socioService.crearSocio(dto);
        return ResponseEntity.status(201).body(socioCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Validated(OnUpdate.class) SocioRequestDTO dto
    ) {
        SocioResponseDTO socioActualizado = socioService.actualizarSocio(id, dto);
        return ResponseEntity.ok(socioActualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioResponseDTO> obtenerPorId(@PathVariable Long id) {
        SocioResponseDTO socio = socioService.obtenerSocioPorId(id);
        return ResponseEntity.ok(socio);
    }
}