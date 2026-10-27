package emberdeep.engine.world.map;

import java.util.HashMap;
import java.util.Map;

/**
 * TileTypes
 */
public class TileTypes {

    private final Map<Character, TileType> cache = new HashMap<>();
    private int created = 0;

    public TileType get(char glyph) {
        return cache.computeIfAbsent(glyph, this::create);
    }

    public int createdCount() { return created; }

    private TileType create(char glyph) {
        TileType type = switch(glyph) {
            case '#' -> new TileType('#', "gray", false);
            case '.' -> new TileType('.', "white", true);
            case '+' -> new TileType('+', "brown", true);
            case '>' -> new TileType('>', "cyan", true);
            case '^' -> new TileType('^', "red", true);
            default -> throw new IllegalArgumentException(
                    "Casilla desconocida: " + glyph);
        };
        created++;
        return type;
    }
}
