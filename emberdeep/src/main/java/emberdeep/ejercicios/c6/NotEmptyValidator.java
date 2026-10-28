package emberdeep.ejercicios.c6;

/**
 * NotEmptyValidator
 */
public class NotEmptyValidator implements CommandValidator {
    private final CommandValidator next;
    public NotEmptyValidator(CommandValidator next) { this.next = next; } 

    @Override
    public String validate(String command) {
        if (command.isBlank())
            return "El comando está vacío";
        return next.validate(command);
    }
}
