package emberdeep.ejercicios.c6;

import java.util.List;
import java.util.Optional;

/**
 * ChainMain
 */
public class ChainMain {

    public static void main(String[] args) {
        CommandValidator end = (command) -> null;

        CommandValidator chain = new NotEmptyValidator(new MaxLengthValidator(new OnlyLettersValidator(end)));

        List<String> toValidate = List.of("", "atacar", "ata2", "ataquemuyfuerte", "ataque2muy");

        toValidate.forEach(command -> {
            String message = Optional.ofNullable(chain.validate(command)).orElseGet(() -> "ok");

            System.out.println("'" + command + "' -> " + message);
        });
    }
    
}
