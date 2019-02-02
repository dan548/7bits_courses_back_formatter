package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.lexer.token.IToken;

import java.util.ArrayDeque;
import java.util.Queue;

public final class LexerContext {

    private int lastSymbol;
    private StringBuilder buffer;
    private Queue<IToken> tokenQueue;

    public LexerContext() {
        lastSymbol = -1;
        buffer = new StringBuilder();
        tokenQueue = new ArrayDeque<>();
    }

    public int getLastSymbol() {
        return lastSymbol;
    }

    public void setLastSymbol(final int lastSymbol) {
        this.lastSymbol = lastSymbol;
    }

    public StringBuilder getBuffer() {
        return buffer;
    }

    public boolean offer(final IToken token) {
        return tokenQueue.offer(token);
    }

    public IToken poll() {
        return tokenQueue.poll();
    }

    public Queue<IToken> getTokenQueue() {
        return tokenQueue;
    }
}
