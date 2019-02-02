package it.sevenbits.formatter.lexer.token;

import java.util.HashMap;
import java.util.Map;

public final class LexemeMapper {

    private final Map<Character, String> map;

    public LexemeMapper() {
        this.map = new HashMap<>();

        map.put('{', "L_CURLY");
        map.put('}', "R_CURLY");
        map.put('(', "L_BRACE");
        map.put(')', "R_BRACE");
        map.put(';', "SEMICOLON");
        map.put(',', "COMMA");

    }

    public String getLexemeName(final char c) {
        return map.getOrDefault(c, "UNKNOWN");
    }
}
