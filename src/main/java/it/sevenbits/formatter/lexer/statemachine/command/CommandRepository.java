package it.sevenbits.formatter.lexer.statemachine.command;

import it.sevenbits.formatter.lexer.CharWrapper;
import it.sevenbits.formatter.lexer.LexerContext;
import it.sevenbits.formatter.lexer.statemachine.LexerState;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository {

    private final Map<ImmutablePair<LexerState, String>, ICommand> map;
    private final ICommand ignore;

    public CommandRepository(final LexerContext lexerContext) {
        this.map = new HashMap<>();
        ignore = new IgnoreCommand();

        LexerState startState = new LexerState("NO_TOKEN");
        LexerState word = new LexerState("WORD");
        LexerState literal = new LexerState("LITERAL");
        LexerState startComment = new LexerState("COMMENT_START");
        LexerState lineComment = new LexerState("COMMENT_LINE");

        ICommand append = new AppendCommand(lexerContext);
        ICommand buildFromBuffer = new BuildTokenFromBufferCommand(lexerContext);

        map.put(new ImmutablePair<>(startState, "SPACING"), ignore);
        map.put(new ImmutablePair<>(startState, "SPECIAL"), new BuildTokenFromLastCommand(lexerContext));
        map.put(new ImmutablePair<>(startState, "QUOTES"), append);
        map.put(new ImmutablePair<>(startState, "LETTER"), append);

        map.put(new ImmutablePair<>(word, "SPACING"), buildFromBuffer);
        map.put(new ImmutablePair<>(word, "SPECIAL"), new BuildFromBufThenFromLastCommand(lexerContext));
        map.put(new ImmutablePair<>(word, "LETTER"), append);

        map.put(new ImmutablePair<>(literal, "SPACING"), append);
        map.put(new ImmutablePair<>(literal, "SPECIAL"), append);
        map.put(new ImmutablePair<>(literal, "QUOTES"), new AppendAndBuildFromBufferCommand(lexerContext));
        map.put(new ImmutablePair<>(literal, "LETTER"), append);

        map.put(new ImmutablePair<>(startState, "SLASH"), append);
        map.put(new ImmutablePair<>(startComment, "SLASH"), append);
        map.put(new ImmutablePair<>(word, "SLASH"), append);
        map.put(new ImmutablePair<>(literal, "SLASH"), append);
        map.put(new ImmutablePair<>(lineComment, "QUOTES"), append);
        map.put(new ImmutablePair<>(lineComment, "SPACING"), append);
        map.put(new ImmutablePair<>(lineComment, "SPECIAL"), append);
        map.put(new ImmutablePair<>(lineComment, "LETTER"), append);
        map.put(new ImmutablePair<>(lineComment, "SLASH"), append);
        map.put(new ImmutablePair<>(lineComment, "NEXT_LINE"), new BuildCommentFromBufferCommand(lexerContext));
        map.put(new ImmutablePair<>(startState, "NEXT_LINE"), ignore);
        map.put(new ImmutablePair<>(word, "NEXT_LINE"), buildFromBuffer);
        map.put(new ImmutablePair<>(literal, "NEXT_LINE"), append);

    }

    public ICommand getCommand(final LexerState state, final char symbol) {
        return map.getOrDefault(new ImmutablePair<>(state, new CharWrapper(symbol).getCodename()), ignore);
    }

}
