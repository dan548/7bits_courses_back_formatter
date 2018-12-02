package it.sevenbits.formatter.io.exceptions;

/**
 * Contains error codes for WriteException.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public enum WritingErrorCode {
    /**
     * Error code values.
     */
    ERROR("Error");

    private String message;

    /**
     * Constructs an error code.
     * @param msg detail message for WriteException represented by the specified code.
     */
    WritingErrorCode(final String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
