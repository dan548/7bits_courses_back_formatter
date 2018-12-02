package it.sevenbits.formatter.io.implementations;

import it.sevenbits.formatter.io.exceptions.ReadException;
import it.sevenbits.formatter.io.exceptions.ReadingErrorCode;
import it.sevenbits.formatter.io.interfaces.IReader;

/**
 * String realization of IReader interface.
 * @author Daniil Polyakov
 * @since 1.0
 * @version 1.0
 */
public class StringReader implements IReader {

    private String input;
    private int current;

    /**
     * Constructs a string realization of IWriter interface.
     * @param s a string to read from.
     */
    public StringReader(final String s) {
        input = s;
        current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < input.length();
    }

    @Override
    public int read() throws ReadException {
        if (current >= input.length()) {
            throw new ReadException(ReadingErrorCode.NO_CHARS);
        } else {
            char c = input.charAt(current);
            current++;
            return c;
        }
    }
}
