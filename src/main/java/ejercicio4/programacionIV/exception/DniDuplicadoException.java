package ejercicio4.programacionIV.exception;

public class DniDuplicadoException extends RuntimeException {

    public DniDuplicadoException() {
        super("El DNI ingresado ya se encuentra registrado.");
    }
}
