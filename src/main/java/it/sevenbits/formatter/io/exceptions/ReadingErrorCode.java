package it.sevenbits.formatter.io.exceptions;

/**
 * Contains error codes for ReadException.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public enum ReadingErrorCode {

    /**
     * Error code values.
     */
    NO_CHARS("Nothing to read"), FILE_NOT_FOUND("The file doesn't exist"), DIRECTORY_FOUND("File not found. The path is a directory.");

    private String message;

    /**
     * Constructs an error code.
     * @param msg detail message for ReadException represented by the specified code.
     */
    ReadingErrorCode(final String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
