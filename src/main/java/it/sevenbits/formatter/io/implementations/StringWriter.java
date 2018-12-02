package it.sevenbits.formatter.io.implementations;

import it.sevenbits.formatter.io.interfaces.IWriter;

/**
 * String realization of IWriter interface.
 * @author Daniil Polyakov
 * @since 1.0
 * @version 1.0
 */
public class StringWriter implements IWriter {

    private StringBuilder buffer;

    /**
     * Constructs a string realization of IWriter interface.
     */
    public StringWriter() {
        this.buffer = new StringBuilder();
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    @Override
    public void write(final int c) {
        buffer.append((char) c);
    }
}
