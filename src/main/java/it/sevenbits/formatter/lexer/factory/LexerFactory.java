package it.sevenbits.formatter.lexer.factory;

import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.Lexer;

/**
 * Basic lexer factory implementation.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class LexerFactory implements ILexerFactory {

    @Override
    public ILexer createLexer(final IReader reader) {
        return new Lexer(reader);
    }
}
