package it.sevenbits.formatter.lexer;

/**
 * Exceptions occurring in the lexer processes.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class LexerException extends Exception {

    /**
     * Constructs a LexerException.
     * @param message message for exception
     */
    public LexerException(final String message) {
        super(message);
    }
}
