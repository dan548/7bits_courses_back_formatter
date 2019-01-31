package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.exceptions.WriteException;
import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.io.interfaces.IWriter;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.LexerException;
import it.sevenbits.formatter.lexer.factory.ILexerFactory;
import it.sevenbits.formatter.lexer.token.IToken;

import java.io.IOException;

/**
 * Class used to format your code.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class Formatter implements IFormatter {

    private final ILexerFactory factory;

    /**
     * Constructs a formatter.
     * @param factory factory which will be used to create a lexer
     */
    public Formatter(final ILexerFactory factory) {
        this.factory = factory;
    }

    /**
     * Formats your code.
     * @param reader character reading object
     * @param writer character writing object
     */
    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        ILexer lexer = factory.createLexer(reader);
        IToken currToken;

        int indentationCount = 0;
        String prevLexeme = null;

        while (lexer.hasToken()) {
            try {
                currToken = lexer.getToken();
            } catch (LexerException e) {
                throw new FormatterException("lex error", e);
            }
            if ("WORD".equals(prevLexeme) && ("L_CURLY".equals(currToken.getName()) || "WORD".equals(currToken.getName()))) {
                try {
                    writer.write(' ');
                } catch (WriteException | IOException e) {
                    throw new FormatterException("writing error", e);
                }
            }
            if ("SEMICOLON".equals(prevLexeme) && !"R_CURLY".equals(currToken.getName())) {
                try {
                    writer.write('\n');
                } catch (WriteException | IOException e) {
                    throw new FormatterException("writing error", e);
                }
            }
            switch (currToken.getName()) {
                case "WORD":
                    try {
                        writeLexeme(writer, currToken.getLexeme());
                    } catch (IOException | WriteException e) {
                        throw new FormatterException("writing error", e);
                    }
                    prevLexeme = currToken.getName();
                    break;
                case "COMMA":
                    try {
                        writeLexeme(writer, currToken.getLexeme());
                        writer.write(' ');
                    } catch (WriteException | IOException e) {
                        throw new FormatterException("writing error", e);
                    }
                    prevLexeme = currToken.getName();
                    break;
                case "SEMICOLON":
                    try {
                        writeLexeme(writer, currToken.getLexeme());
                    } catch (WriteException | IOException e) {
                        throw new FormatterException("writing error", e);
                    }
                    prevLexeme = currToken.getName();
                    break;
                case "L_CURLY":
                    try {
                        writeLexeme(writer, currToken.getLexeme());
                        writer.write('\n');
                        indentationCount++;
                        indentWithFourSpacesNTimes(writer, indentationCount);
                    } catch (WriteException | IOException e) {
                        throw new FormatterException("writing error", e);
                    }
                    prevLexeme = currToken.getName();
                    break;
                case "R_CURLY":
                    try {
                        writer.write('\n');
                        indentationCount--;
                        indentWithFourSpacesNTimes(writer, indentationCount);
                        writeLexeme(writer, currToken.getLexeme());
                    } catch (WriteException | IOException e) {
                        throw new FormatterException("writing error", e);
                    }
                    prevLexeme = currToken.getName();
                    break;
                    default:
                        try {
                            writeLexeme(writer, currToken.getLexeme());
                        } catch (WriteException | IOException e) {
                            throw new FormatterException("writing error", e);
                        }
                        prevLexeme = currToken.getName();
                        break;
            }
        }
    }

    private void indentWithFourSpacesNTimes(final IWriter writer, final int n) {
        for (int i = 0; i < n; i++) {
            try {
                writer.write(' ');
                writer.write(' ');
                writer.write(' ');
                writer.write(' ');
            } catch (WriteException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeLexeme(final IWriter writer, final String lexeme) throws IOException, WriteException {
        for (int i = 0; i < lexeme.length(); i++) {
            writer.write(lexeme.charAt(i));
        }
    }
}
