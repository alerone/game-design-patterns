package emberdeep.utils;

/**
 * TermColors
 */
public class TermColors {

    private class Colours {
        public static final String RESET = "\u001B[0m";

        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
    }

    public static String toRed(String s) { return Colours.RED + s + Colours.RESET; } 
    public static String toGreen(String s) { return Colours.GREEN + s + Colours.RESET; } 
    public static String toYellow(String s) { return Colours.YELLOW + s + Colours.RESET; } 
    public static String toBlue(String s) { return Colours.BLUE + s + Colours.RESET; } 
    public static String toPurple(String s) { return Colours.PURPLE + s + Colours.RESET; } 
    public static String toCyan(String s) { return Colours.CYAN + s + Colours.RESET; } 
    public static String toWhite(String s) { return Colours.WHITE + s + Colours.RESET; } 
}
