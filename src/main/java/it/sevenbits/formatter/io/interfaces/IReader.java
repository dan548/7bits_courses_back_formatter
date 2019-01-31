package it.sevenbits.formatter.io.interfaces;

import it.sevenbits.formatter.io.exceptions.ReadException;

import java.io.IOException;

/**
 * Interface to read characters from different sources.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public interface IReader {

    /**
     * Checks if there is any symbol left.
     * @return if there is any char left in the inner stream.
     * @throws IOException if some error occurred in the process of reading from file
     */
    boolean hasNext() throws IOException;
    /**
     * Returns next symbol.
     * @return next character of the stream.
     * @throws ReadException if some kind of reading error has occurred (for example, there are no characters left).
     * @throws IOException input/output error
     */
    int read() throws ReadException, IOException;

}
