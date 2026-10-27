package emberdeep.ejercicios.c6;

import lombok.RequiredArgsConstructor;

/**
 * PlainText
 */
@RequiredArgsConstructor 
public class PlainText implements TextSource{
    private final String text;

    @Override
    public String text() {
        return text;
    }

    
}
