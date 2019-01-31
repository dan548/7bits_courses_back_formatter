package it.sevenbits.formatter.formatter;

/**
 * The class represents exceptions that are caused in the process of formatting.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class FormatterException extends Exception {

    /**
     * Constructs a new FormatterException with the specified message.
     * @param message exception type message
     */
    public FormatterException(final String message) {
        super(message);
    }

    /**
     * Constructs a new FormatterException with the specified message and exception caused this.
     * @param message exception type message
     * @param cause exception that caused the exception
     */
    public FormatterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
