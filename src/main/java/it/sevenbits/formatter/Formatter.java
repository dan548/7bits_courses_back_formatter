package it.sevenbits.formatter;

import it.sevenbits.formatter.io.exceptions.ReadException;
import it.sevenbits.formatter.io.exceptions.WriteException;
import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.io.interfaces.IWriter;

import java.io.IOException;

/**
 * Class used to format your code.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class Formatter {

    private boolean isLetter(final int c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    /**
     * Formats your code.
     * @param reader character reading object
     * @param writer character writing object
     */
    public void format(final IReader reader, final IWriter writer) {

        int currentSymbol;
        int indentCounter = 0;
        boolean nextLineIndent = false;
        boolean isLetter = false;
        boolean isSpaceNeeded = false;

        while (reader.hasNext()) {
            try {
                currentSymbol = reader.read();
                if (nextLineIndent && currentSymbol != '}' && currentSymbol != ' ' && currentSymbol != '\n') {
                    try {
                        writer.write('\n');
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }
                    indentWithFourSpacesNTimes(writer, indentCounter);
                    nextLineIndent = false;
                }
                switch (currentSymbol) {
                    case '{':
                        try {
                            writer.write(' ');
                            writer.write(currentSymbol);
                            writer.write('\n');
                            indentCounter++;
                            indentWithFourSpacesNTimes(writer, indentCounter);
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                        isLetter = false;
                        isSpaceNeeded = false;
                        break;
                    case '}':
                        try {
                            writer.write('\n');
                            indentCounter--;
                            indentWithFourSpacesNTimes(writer, indentCounter);
                            writer.write(currentSymbol);
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                        isLetter = false;
                        break;
                    case ';':
                        try {
                            writer.write(currentSymbol);
                            nextLineIndent = true;
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                        isLetter = false;
                        break;
                    case ',':
                        try {
                            writer.write(currentSymbol);
                            writer.write(' ');
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                        isLetter = false;
                        break;
                    case ' ':
                        if (isLetter) {
                            isSpaceNeeded = true;
                        }
                        break;
                    case '\n':
                        if (isLetter) {
                            isSpaceNeeded = true;
                        }
                        break;
                    case '(':
                        isSpaceNeeded = false;
                    case '"':
                        isSpaceNeeded = false;
                    default:
                        isLetter = isLetter(currentSymbol);
                        if (isSpaceNeeded && isLetter) {
                            try {
                                writer.write(' ');
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            isSpaceNeeded = false;
                        }
                        try {
                            writer.write(currentSymbol);
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } catch (ReadException | IOException e) {
                e.printStackTrace();
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
}
