package it.sevenbits.formatter.lexer.factory;

import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.lexer.ILexer;

/**
 * Lexer factory interface.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public interface ILexerFactory {

    /**
     * Creates a lexer based on a reader.
     * @param reader reader to use
     * @return created lexer
     */
    ILexer createLexer(IReader reader);

}
