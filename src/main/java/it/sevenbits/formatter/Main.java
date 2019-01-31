package it.sevenbits.formatter;

import it.sevenbits.formatter.formatter.Formatter;
import it.sevenbits.formatter.formatter.IFormatter;
import it.sevenbits.formatter.io.implementations.FileReader;
import it.sevenbits.formatter.io.implementations.FileWriter;
import it.sevenbits.formatter.lexer.factory.LexerFactory;

import java.nio.charset.Charset;

/**
 * Main class.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public final class Main {

    private Main(){}

    /**
     * Main method.
     * @param args program arguments
     */
    public static void main(final String[] args) {
        if (args.length == 2) {
            String source = args[0];
            String destination = args[1];
            IFormatter formatter = new Formatter(new LexerFactory());
            try (FileReader reader = new FileReader(source, Charset.forName("UTF-8"));
                 FileWriter writer = new FileWriter(destination, Charset.forName("UTF-8"))
            ) {
                formatter.format(reader, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
