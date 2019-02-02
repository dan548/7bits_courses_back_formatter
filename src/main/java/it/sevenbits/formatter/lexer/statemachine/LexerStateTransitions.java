package it.sevenbits.formatter.lexer.statemachine;

import it.sevenbits.formatter.lexer.CharWrapper;

public final class LexerStateTransitions {

    private final LexerStateMap map;

    public LexerStateTransitions() {
        this.map = new LexerStateMap();
    }

    public LexerState getStartState() {
        return map.getStartState();
    }

    public LexerState nextState(final LexerState state, final int symbol) {
        return map.getNextState(state, new CharWrapper(symbol));
    }
}
