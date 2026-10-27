package emberdeep.engine.world.map;

/**
 * MapData
 */
public class MapData {

    private static final String FLOOR_1 = """
        ############
        #@...#....>#
        #.##.#.##..#
        #.#..+.#.^.#
        #.#.##.#...#
        #...#..+...#
        ############
        """;
    private static final String FLOOR_2 = """
        ############
        #@.....##.^#
        ##....###..#
        #>#........#
        #.#..###...#
        #...#..+...#
        ############
        """;
    private static final String FLOOR_3 = """
        ############
        #@.#.....>.#
        #..#.###.#.#
        #.##.#^..#.#
        #....#.+##.#
        #.####...^.#
        ############
        """;

    private MapData() {}

    public static String floor(int number) {
        return switch (number) {
            case 1 -> FLOOR_1;
            case 2 -> FLOOR_2;
            case 3 -> FLOOR_3;
            default -> throw new 
                IllegalArgumentException("No hay mapa para la planta " + number);
        };
    } 
}
