package it.sevenbits.formatter.io.exceptions;

/**
 * The class represents exceptions that are caused in the process of reading from a stream.
 * @since 1.0
 * @author Daniil Polyakov
 * @version 1.0
 */
public class ReadException extends Exception {

    /**
     * Constructs a new ReadException with the specified error code.
     * @param code error code (type of exception) used to generate specific message.
     */
    public ReadException(final ReadingErrorCode code) {
        super(code.getMessage());
    }
}
