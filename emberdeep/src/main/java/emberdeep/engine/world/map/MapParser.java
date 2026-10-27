package emberdeep.engine.world.map;

/**
 * MapParser
 */
public class MapParser {

    private final TileTypes types;


    public MapParser(TileTypes types) { this.types = types; }
    
    public GameMap parse(String text) {
        String[] rows = text.strip().split("\n");
        int width = rows[0].length();
        int height = rows.length;
        TileType[][] grid = new TileType[height][width];
        int startX = -1;
        int startY = -1;

        for (int y = 0; y < height; y++) {
            if (rows[y].length() != width)
                throw new IllegalArgumentException(
                        "Fila " + (y + 1) + " con otra longitud");
            for (int x = 0; x < width; x++) {
                char glyph = rows[y].charAt(x);
                if (glyph == '@') {
                    startX = x;
                    startY = y;
                    glyph = '.';
                }
                grid[y][x] = types.get(glyph);
            }
        }
        if (startX < 0) 
            throw new IllegalArgumentException("Falta posición inicial '@'");
        return new GameMap(grid, startX, startY);
    }
}
