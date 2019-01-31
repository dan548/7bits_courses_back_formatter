package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.io.exceptions.ReadException;
import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.Token;

import java.io.IOException;

/**
 * Basic lexer implementation.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class Lexer implements ILexer {

    private IReader reader;
    private int lastSymbol;

    /**
     * Constructs a lexer.
     * @param reader reader to use
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
        lastSymbol = -1;
    }

    @Override
    public boolean hasToken() {
        if (lastSymbol != -1) {
            return true;
        }
        try {
            while (reader.hasNext()) {
                lastSymbol = reader.read();
                if (lastSymbol != '\n' && lastSymbol != ' ') {
                    return true;
                }
            }
        } catch (ReadException | IOException e) {
            return false;
        }
        return false;
    }

    @Override
    public IToken getToken() throws LexerException {
        StringBuilder sb = new StringBuilder();
        char c;
        String name = null;
        String lexeme = null;

        if (!hasToken()) {
            throw new LexerException("There are no tokens left!");
        }

        c = (char) lastSymbol;
        sb.append(c);
        lastSymbol = -1;

        if (c == '{' || c == '}' || c == ';' || c == ',' || c == '(' || c == ')') {
            switch (c) {
                case '{':
                    name = "L_CURLY";
                    lexeme = Character.toString(c);
                    break;
                case '}':
                    name = "R_CURLY";
                    lexeme = Character.toString(c);
                    break;
                case ';':
                    name = "SEMICOLON";
                    lexeme = Character.toString(c);
                    break;
                case ',':
                    name = "COMMA";
                    lexeme = Character.toString(c);
                    break;
                case '(':
                    name = "L_BRACE";
                    lexeme = Character.toString(c);
                    break;
                case ')':
                    name = "R_BRACE";
                    lexeme = Character.toString(c);
                    break;
                default:
                    break;
            }
            return new Token(name, lexeme);
        } else {
            try {
                c = (char) reader.read();
            } catch (ReadException | IOException e) {
                throw new LexerException("Bad token.");
            }
            while (c != '{' && c != '}' && c != ';' && c != ',' && c != '(' && c != ')' && c != ' ' && c != '\n') {
                sb.append(c);
                try {
                    c = (char) reader.read();
                } catch (ReadException | IOException e) {
                    throw new LexerException("Bad token.");
                }
            }
            if (c != ' ' && c != '\n') {
                lastSymbol = c;
            }
            return new Token("WORD", sb.toString());
        }
    }
}
