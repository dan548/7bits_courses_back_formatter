package it.sevenbits.formatter.formatter.statemachine.command;

import it.sevenbits.formatter.formatter.FormatterContext;
import it.sevenbits.formatter.formatter.statemachine.FormatterState;
import it.sevenbits.formatter.lexer.token.IToken;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

public class FCommandRepository {

    private final Map<ImmutablePair<FormatterState, String>, IFCommand> map;
    private final FormatterContext formatter;
    private final IFCommand writeToken;

    public FCommandRepository(final FormatterContext context) {
        formatter = context;

        writeToken = new WriteTokenCommand(formatter);

        map = new HashMap<>();

        IFCommand spacedWord = new SpacedWordCommand(formatter);
        IFCommand nextLineIndentWord = new NextLineIndentWordCommand(formatter);
        IFCommand nextLineIncrementIndentWord = new NextLineIncIndLexemeCommand(formatter);
        IFCommand nextLineDecrementIndentWord = new NextLineDecIndLexemeCommand(formatter);

        FormatterState startState = new FormatterState("START");
        FormatterState defaultState = new FormatterState("DEFAULT");
        FormatterState rBraceState = new FormatterState("R_BRACE");
        FormatterState lCurlyState = new FormatterState("L_CURLY");
        FormatterState rCurlyState = new FormatterState("R_CURLY");
        FormatterState semicolonState = new FormatterState("SEMICOLON");
        FormatterState commaState = new FormatterState("COMMA");
        FormatterState wordState = new FormatterState("WORD");
        FormatterState lineCommentState = new FormatterState("LINE_COMMENT");

        map.put(new ImmutablePair<>(startState, "WORD"), writeToken);
        map.put(new ImmutablePair<>(startState, "L_CURLY"), writeToken);
        map.put(new ImmutablePair<>(startState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(wordState, "WORD"), spacedWord);
        map.put(new ImmutablePair<>(wordState, "L_CURLY"), spacedWord);
        map.put(new ImmutablePair<>(wordState, "COMMA"), writeToken);
        map.put(new ImmutablePair<>(wordState, "SEMICOLON"), writeToken);
        map.put(new ImmutablePair<>(wordState, "L_BRACE"), writeToken);
        map.put(new ImmutablePair<>(wordState, "R_BRACE"), writeToken);
        map.put(new ImmutablePair<>(wordState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(commaState, "WORD"), spacedWord);
        map.put(new ImmutablePair<>(commaState, "LITERAL"), spacedWord);
        map.put(new ImmutablePair<>(commaState, "L_BRACE"), spacedWord);
        map.put(new ImmutablePair<>(commaState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(semicolonState, "WORD"), nextLineIndentWord);
        map.put(new ImmutablePair<>(semicolonState, "LITERAL"), nextLineIndentWord);
        map.put(new ImmutablePair<>(semicolonState, "R_CURLY"), nextLineDecrementIndentWord);
        map.put(new ImmutablePair<>(semicolonState, "L_BRACE"), nextLineIndentWord);
        map.put(new ImmutablePair<>(semicolonState, "R_BRACE"), writeToken);
        map.put(new ImmutablePair<>(semicolonState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(defaultState, "WORD"), writeToken);
        map.put(new ImmutablePair<>(defaultState, "LITERAL"), writeToken);
        map.put(new ImmutablePair<>(defaultState, "COMMA"), writeToken);
        map.put(new ImmutablePair<>(defaultState, "L_BRACE"), writeToken);
        map.put(new ImmutablePair<>(defaultState, "R_BRACE"), writeToken);
        map.put(new ImmutablePair<>(defaultState, "L_CURLY"), writeToken);
        map.put(new ImmutablePair<>(defaultState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(rBraceState, "WORD"), spacedWord);
        map.put(new ImmutablePair<>(rBraceState, "LITERAL"), spacedWord);
        map.put(new ImmutablePair<>(rBraceState, "COMMA"), writeToken);
        map.put(new ImmutablePair<>(rBraceState, "SEMICOLON"), writeToken);
        map.put(new ImmutablePair<>(rBraceState, "L_BRACE"), spacedWord);
        map.put(new ImmutablePair<>(rBraceState, "R_BRACE"), writeToken);
        map.put(new ImmutablePair<>(rBraceState, "L_CURLY"), spacedWord);
        map.put(new ImmutablePair<>(rBraceState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(rCurlyState, "WORD"), nextLineIndentWord);
        map.put(new ImmutablePair<>(rCurlyState, "LITERAL"), nextLineIndentWord);
        map.put(new ImmutablePair<>(rCurlyState, "R_CURLY"), nextLineDecrementIndentWord);
        map.put(new ImmutablePair<>(rCurlyState, "SEMICOLON"), writeToken);
        map.put(new ImmutablePair<>(rCurlyState, "L_BRACE"), nextLineIndentWord);
        map.put(new ImmutablePair<>(rCurlyState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(lCurlyState, "WORD"), nextLineIncrementIndentWord);
        map.put(new ImmutablePair<>(lCurlyState, "LITERAL"), nextLineIncrementIndentWord);
        map.put(new ImmutablePair<>(lCurlyState, "R_CURLY"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lCurlyState, "L_CURLY"), nextLineIncrementIndentWord);
        map.put(new ImmutablePair<>(lCurlyState, "L_BRACE"), nextLineIncrementIndentWord);
        map.put(new ImmutablePair<>(lCurlyState, "LINE_COMMENT"), nextLineIndentWord);

        map.put(new ImmutablePair<>(lineCommentState, "WORD"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "LITERAL"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "COMMA"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "SEMICOLON"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "L_BRACE"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "R_BRACE"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "L_CURLY"), nextLineIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "R_CURLY"), nextLineDecrementIndentWord);
        map.put(new ImmutablePair<>(lineCommentState, "LINE_COMMENT"), nextLineIndentWord);
    }

    public IFCommand getCommand(final FormatterState state, final IToken token) {
        formatter.setLastToken(token);
        return map.getOrDefault(new ImmutablePair<>(state, token.getName()), writeToken);
    }
}