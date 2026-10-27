package emberdeep.engine.world.map;

/**
 * GridMap
 */
public class GameMap {

    private final TileType[][] grid;
    private int x;
    private int y;

    public GameMap(TileType[][] grid, int startX, int startY) {
        this.grid = grid;
        this.x = startX;
        this.y = startY;
    }

    public int width() {
        return grid[0].length;
    }

    public int height() {
        return grid.length;
    }

    public int partyX() {
        return x;
    }

    public int partyY() {
        return y;
    }

    public TileType partyTile() {
        return tileAt(x, y);
    }

    public boolean onStairs() {
        return partyTile().glyph() == '>';
    }

    public TileType tileAt(int x, int y) {
        return grid[y][x];
    }

    public boolean move(int dx, int dy) {
        int nx = x + dx;
        int ny = y + dy;

        if (nx < 0 || ny < 0 || nx >= width() || ny >= height()
                || !grid[ny][nx].walkeable()) {
            return false;
        }

        x = nx;
        y = ny;
        return true;
    }

    public String render() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                boolean party = row == y && col == x;
                result.append(party ? '@' : grid[row][col].glyph());
            }
            result.append('\n');
        }

        return result.toString();
    }
}
