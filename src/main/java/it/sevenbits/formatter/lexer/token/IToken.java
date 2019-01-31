package it.sevenbits.formatter.lexer.token;

/**
 * Token interface.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public interface IToken {

    /**
     * Returns token's name.
     * @return token name
     */
    String getName();

    /**
     * Returns token's lexeme.
     * @return lexeme represented by token
     */
    String getLexeme();

}
