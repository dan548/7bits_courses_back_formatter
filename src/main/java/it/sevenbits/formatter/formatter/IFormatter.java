package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.io.interfaces.IWriter;

/**
 * Your code formatter interface.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public interface IFormatter {
    /**
     * Formats your code.
     * @param reader character reading interface
     * @param writer character writing interface
     * @throws FormatterException formatting error
     */
    void format(IReader reader, IWriter writer) throws FormatterException;

}
