package ejercicio4.programacionIV.service;

import ejercicio4.programacionIV.dto.DireccionDTO;
import ejercicio4.programacionIV.dto.SocioResponseDTO;
import ejercicio4.programacionIV.entity.Socio;
import ejercicio4.programacionIV.repository.SocioRepository;
import ejercicio4.programacionIV.dto.SocioRequestDTO;
import ejercicio4.programacionIV.exception.DniDuplicadoException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class SocioService {

    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    public SocioResponseDTO crearSocio(SocioRequestDTO dto) {
        if (socioRepository.existsByDni(dto.getDni())) {
            throw new DniDuplicadoException();
        }

        Socio socio = new Socio();
        socio.setDni(dto.getDni());
        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());
        socio.setEmail(dto.getEmail());
        socio.setPassword(dto.getPassword());
        socio.setFechaNacimiento(dto.getFechaNacimiento());
        socio.setFechaRegistro(LocalDateTime.now());

        DireccionDTO direccion = dto.getDireccion();
        socio.setCalle(direccion.getCalle());
        socio.setCiudad(direccion.getCiudad());
        socio.setProvincia(direccion.getProvincia());
        socio.setCodigoPostal(direccion.getCodigoPostal());

        Socio socioGuardado = socioRepository.save(socio);
        return toResponseDTO(socioGuardado);
    }

    public SocioResponseDTO actualizarSocio(Long id, SocioRequestDTO dto) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Socio no encontrado"));

        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());

        if (dto.getEmail() != null) {
            socio.setEmail(dto.getEmail());
        }

        if (dto.getFechaNacimiento() != null) {
            socio.setFechaNacimiento(dto.getFechaNacimiento());
        }

        if (dto.getDireccion() != null) {
            DireccionDTO direccion = dto.getDireccion();
            socio.setCalle(direccion.getCalle());
            socio.setCiudad(direccion.getCiudad());
            socio.setProvincia(direccion.getProvincia());
            socio.setCodigoPostal(direccion.getCodigoPostal());
        }

        Socio socioActualizado = socioRepository.save(socio);
        return toResponseDTO(socioActualizado);
    }

    public SocioResponseDTO obtenerSocioPorId(Long id) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Socio no encontrado"));
        return toResponseDTO(socio);
    }

    private SocioResponseDTO toResponseDTO(Socio socio) {
        SocioResponseDTO responseDTO = new SocioResponseDTO();
        responseDTO.setId(socio.getId());
        responseDTO.setNombre(socio.getNombre());
        responseDTO.setApellido(socio.getApellido());
        responseDTO.setEmail(socio.getEmail());
        responseDTO.setFechaRegistro(socio.getFechaRegistro());
        responseDTO.setEstadoMembresia(calcularEstadoMembresia(socio.getFechaVencimientoMembresia()));

        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setCalle(socio.getCalle());
        direccionDTO.setCiudad(socio.getCiudad());
        direccionDTO.setProvincia(socio.getProvincia());
        direccionDTO.setCodigoPostal(socio.getCodigoPostal());
        responseDTO.setDireccion(direccionDTO);

        return responseDTO;
    }

    private String calcularEstadoMembresia(LocalDate fechaVencimientoMembresia) {
        if (fechaVencimientoMembresia == null) {
            return "SIN MEMBRESIA";
        }
        if (fechaVencimientoMembresia.isAfter(LocalDate.now())) {
            return "ACTIVO";
        }
        return "VENCIDO";
    }
}