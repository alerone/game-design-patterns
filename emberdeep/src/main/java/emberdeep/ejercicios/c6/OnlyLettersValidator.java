package emberdeep.ejercicios.c6;

/**
 * OnlyLettersValidator
 */
public class OnlyLettersValidator implements CommandValidator{
    private final CommandValidator next;

    public OnlyLettersValidator(CommandValidator next) { this.next = next; }

    @Override
    public String validate(String command) {
        if(command.chars().anyMatch(c -> !Character.isLetter(c)))
            return "Solo se admiten letras";
        return next.validate(command);
    }

    
}
