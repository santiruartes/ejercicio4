package ejercicio4.programacionIV.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ejercicio4.programacionIV.validation.DniValidator;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DniValidator.class)
public @interface DniValido {

    String message() default "El DNI debe contener entre 7 y 8 dígitos sin puntos ni espacios";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}