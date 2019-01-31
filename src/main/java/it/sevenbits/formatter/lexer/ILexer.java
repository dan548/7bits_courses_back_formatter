package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Lexer interface.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public interface ILexer {

    /**
     * Checks if there is any token left.
     * @return false if there are no tokens left, true - otherwise.
     */
    boolean hasToken();

    /**
     * Returns the following token.
     * @return next token
     * @throws LexerException thrown if there are no tokens left or there is some reading exception.
     */
    IToken getToken() throws LexerException;

}
