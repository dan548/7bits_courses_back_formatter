package it.sevenbits.formatter.io.exceptions;

/**
 * The class represents exceptions that are caused in the process of writing to a stream.
 * @since 1.0
 * @author Daniil Polyakov
 * @version 1.0
 */
public class WriteException extends Exception {

    /**
     * Constructs a new WriteException with the specified error code.
     * @param code error code (type of exception) used to generate specific message.
     */
    public WriteException(final WritingErrorCode code) {
        super(code.getMessage());
    }

}
