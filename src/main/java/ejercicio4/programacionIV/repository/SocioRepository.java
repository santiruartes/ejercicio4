package ejercicio4.programacionIV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ejercicio4.programacionIV.entity.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long> {

    boolean existsByDni(String dni);

}