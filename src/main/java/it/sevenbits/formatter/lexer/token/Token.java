package it.sevenbits.formatter.lexer.token;

/**
 * Basic token implementation.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class Token implements IToken {

    private String name;
    private String lexeme;

    /**
     * Constructs a token.
     * @param name token name
     * @param lexeme token lexeme
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }
}
