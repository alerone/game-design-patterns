package emberdeep.ejercicios.c6;

/**
 * MaxLengthValidator
 */
public class MaxLengthValidator implements CommandValidator{
    private final CommandValidator next;

    public MaxLengthValidator(CommandValidator next) { this.next = next; }

    @Override
    public String validate(String command) {
        if(command.length() > 8) return "El comando tiene más de 8 carácteres";

        return next.validate(command);
    }

}
