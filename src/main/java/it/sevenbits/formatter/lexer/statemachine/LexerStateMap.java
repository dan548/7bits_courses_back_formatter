package it.sevenbits.formatter.lexer.statemachine;

import it.sevenbits.formatter.lexer.CharWrapper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

class LexerStateMap {

    private final LexerState startState = new LexerState("NO_TOKEN");

    private final Map<ImmutablePair<LexerState, String>, LexerState> states;

    LexerStateMap() {
        this.states = new HashMap<>();

        LexerState word = new LexerState("WORD");
        LexerState literal = new LexerState("LITERAL");
        LexerState startComment = new LexerState("COMMENT_START");
        LexerState lineComment = new LexerState("COMMENT_LINE");

        states.put(new ImmutablePair<>(startState, "SPACING"), startState);
        states.put(new ImmutablePair<>(startState, "SPECIAL"), startState);
        states.put(new ImmutablePair<>(startState, "QUOTES"), literal);
        states.put(new ImmutablePair<>(startState, "LETTER"), word);
        states.put(new ImmutablePair<>(word, "SPACING"), startState);
        states.put(new ImmutablePair<>(word, "SPECIAL"), startState);
        states.put(new ImmutablePair<>(word, "LETTER"), word);
        states.put(new ImmutablePair<>(literal, "QUOTES"), startState);
        states.put(new ImmutablePair<>(literal, "SPACING"), literal);
        states.put(new ImmutablePair<>(literal, "SPECIAL"), literal);
        states.put(new ImmutablePair<>(literal, "LETTER"), literal);

        states.put(new ImmutablePair<>(startState, "SLASH"), startComment);
        states.put(new ImmutablePair<>(startComment, "SLASH"), lineComment);
        states.put(new ImmutablePair<>(word, "SLASH"), word);
        states.put(new ImmutablePair<>(literal, "SLASH"), lineComment);
        states.put(new ImmutablePair<>(lineComment, "QUOTES"), lineComment);
        states.put(new ImmutablePair<>(lineComment, "SPACING"), lineComment);
        states.put(new ImmutablePair<>(lineComment, "SPECIAL"), lineComment);
        states.put(new ImmutablePair<>(lineComment, "LETTER"), lineComment);
        states.put(new ImmutablePair<>(lineComment, "SLASH"), lineComment);
        states.put(new ImmutablePair<>(lineComment, "NEXT_LINE"), startState);
        states.put(new ImmutablePair<>(startState, "NEXT_LINE"), startState);
        states.put(new ImmutablePair<>(word, "NEXT_LINE"), startState);
        states.put(new ImmutablePair<>(literal, "NEXT_LINE"), literal);
    }

    public LexerState getStartState() {
        return startState;
    }

    public LexerState getNextState(final LexerState state, final CharWrapper symbol) {
        return states.getOrDefault(new ImmutablePair<>(state, symbol.getCodename()), startState);
    }
}
