package emberdeep.ejercicios.c6;

/**
 * CommandValidator
 */
@FunctionalInterface 
public interface CommandValidator {
    String validate(String command);
}
