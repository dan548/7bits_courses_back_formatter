package it.sevenbits.formatter.lexer;

import java.util.HashMap;
import java.util.Map;

public class CharWrapper {

    private final String codename;
    private final int symbol;

    private static final Map<Integer, String> map;

    static {
        map = new HashMap<>();

        map.put((int) '\n', "NEXT_LINE");
        map.put((int) ' ', "SPACING");

        map.put((int) '{', "SPECIAL");
        map.put((int) '}', "SPECIAL");
        map.put((int) '(', "SPECIAL");
        map.put((int) ')', "SPECIAL");
        map.put((int) ',', "SPECIAL");
        map.put((int) ';', "SPECIAL");

        map.put((int) '"', "QUOTES");

        map.put((int) '/', "SLASH");
    }

    public CharWrapper(final int symbol) {
        this.symbol = symbol;
        this.codename = map.getOrDefault(symbol, "LETTER");
    }

    public String getCodename() {
        return codename;
    }

    public int getSymbol() {
        return symbol;
    }
}
