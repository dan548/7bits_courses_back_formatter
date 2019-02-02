package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.interfaces.IWriter;
import it.sevenbits.formatter.lexer.token.IToken;

public final class FormatterContext {

    private IToken lastToken;
    private int indentationCount;
    private IWriter writer;

    public FormatterContext() {
        lastToken = null;
        indentationCount = 0;
        writer = null;
    }

    public IToken getLastToken() {
        return lastToken;
    }

    public void setLastToken(final IToken lastToken) {
        this.lastToken = lastToken;
    }

    public int getIndentationCount() {
        return indentationCount;
    }

    public void incrementIndentationCount() {
        indentationCount++;
    }

    public void decrementIndentationCount() {
        indentationCount--;
    }

    public void setWriter(final IWriter writer) {
        this.writer = writer;
    }

    public IWriter getWriter() {
        return writer;
    }
}
