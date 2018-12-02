package it.sevenbits.formatter.io.interfaces;

import it.sevenbits.formatter.io.exceptions.WriteException;

import java.io.IOException;

/**
 *
 */
public interface IWriter {

    /**
     * Writes a character.
     * @param c a character to write
     * @throws WriteException if writing is impossible
     * @throws IOException input/output error
     */
    void write(int c) throws WriteException, IOException;

}
