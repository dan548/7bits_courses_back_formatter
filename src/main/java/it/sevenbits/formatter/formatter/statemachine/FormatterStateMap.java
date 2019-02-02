package it.sevenbits.formatter.formatter.statemachine;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

class FormatterStateMap {

    private final FormatterState startState = new FormatterState("START");
    private final FormatterState defaultState = new FormatterState("DEFAULT");

    private final Map<ImmutablePair<FormatterState, String>, FormatterState> states;

    FormatterStateMap() {
        states = new HashMap<>();

        FormatterState rBraceState = new FormatterState("R_BRACE");
        FormatterState lCurlyState = new FormatterState("L_CURLY");
        FormatterState rCurlyState = new FormatterState("R_CURLY");
        FormatterState semicolonState = new FormatterState("SEMICOLON");
        FormatterState commaState = new FormatterState("COMMA");
        FormatterState wordState = new FormatterState("WORD");
        FormatterState lineCommentState = new FormatterState("LINE_COMMENT");

        states.put(new ImmutablePair<>(startState, "WORD"), wordState);
        states.put(new ImmutablePair<>(startState, "L_CURLY"), lCurlyState);
        states.put(new ImmutablePair<>(startState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(wordState, "WORD"), wordState);
        states.put(new ImmutablePair<>(wordState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(wordState, "L_CURLY"), lCurlyState);
        states.put(new ImmutablePair<>(wordState, "R_CURLY"), rCurlyState);
        states.put(new ImmutablePair<>(wordState, "COMMA"), commaState);
        states.put(new ImmutablePair<>(wordState, "SEMICOLON"), semicolonState);
        states.put(new ImmutablePair<>(wordState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(wordState, "R_BRACE"), rBraceState);
        states.put(new ImmutablePair<>(wordState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(commaState, "WORD"), wordState);
        states.put(new ImmutablePair<>(commaState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(commaState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(commaState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(semicolonState, "WORD"), wordState);
        states.put(new ImmutablePair<>(semicolonState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(semicolonState, "R_CURLY"), rCurlyState);
        states.put(new ImmutablePair<>(semicolonState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(semicolonState, "R_BRACE"), rBraceState);
        states.put(new ImmutablePair<>(semicolonState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(defaultState, "WORD"), wordState);
        states.put(new ImmutablePair<>(defaultState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(defaultState, "COMMA"), commaState);
        states.put(new ImmutablePair<>(defaultState, "SEMICOLON"), semicolonState);
        states.put(new ImmutablePair<>(defaultState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(defaultState, "R_BRACE"), rBraceState);
        states.put(new ImmutablePair<>(defaultState, "L_CURLY"), lCurlyState);
        states.put(new ImmutablePair<>(defaultState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(rBraceState, "WORD"), wordState);
        states.put(new ImmutablePair<>(rBraceState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(rBraceState, "COMMA"), commaState);
        states.put(new ImmutablePair<>(rBraceState, "SEMICOLON"), semicolonState);
        states.put(new ImmutablePair<>(rBraceState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(rBraceState, "R_BRACE"), rBraceState);
        states.put(new ImmutablePair<>(rBraceState, "L_CURLY"), lCurlyState);
        states.put(new ImmutablePair<>(rBraceState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(rCurlyState, "WORD"), wordState);
        states.put(new ImmutablePair<>(rCurlyState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(rCurlyState, "R_CURLY"), rCurlyState);
        states.put(new ImmutablePair<>(rCurlyState, "SEMICOLON"), semicolonState);
        states.put(new ImmutablePair<>(rCurlyState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(rCurlyState, "R_BRACE"), rBraceState);
        states.put(new ImmutablePair<>(rCurlyState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(lCurlyState, "WORD"), wordState);
        states.put(new ImmutablePair<>(lCurlyState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(lCurlyState, "L_CURLY"), lCurlyState);
        states.put(new ImmutablePair<>(lCurlyState, "R_CURLY"), rCurlyState);
        states.put(new ImmutablePair<>(lCurlyState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(lCurlyState, "R_BRACE"), rBraceState);
        states.put(new ImmutablePair<>(lCurlyState, "LINE_COMMENT"), lineCommentState);

        states.put(new ImmutablePair<>(lineCommentState, "WORD"), wordState);
        states.put(new ImmutablePair<>(lineCommentState, "LITERAL"), defaultState);
        states.put(new ImmutablePair<>(lineCommentState, "L_CURLY"), lCurlyState);
        states.put(new ImmutablePair<>(lineCommentState, "R_CURLY"), rCurlyState);
        states.put(new ImmutablePair<>(lineCommentState, "COMMA"), commaState);
        states.put(new ImmutablePair<>(lineCommentState, "SEMICOLON"), semicolonState);
        states.put(new ImmutablePair<>(lineCommentState, "L_BRACE"), defaultState);
        states.put(new ImmutablePair<>(lineCommentState, "R_BRACE"), rBraceState);
        states.put(new ImmutablePair<>(lineCommentState, "LINE_COMMENT"), lineCommentState);
    }

    public FormatterState getNextState(final FormatterState state, final String tokenName) {
        return states.getOrDefault(new ImmutablePair<>(state, tokenName), defaultState);
    }

    public FormatterState getStartState() {
        return startState;
    }
}
