package it.sevenbits.formatter.lexer.statemachine.command;

import it.sevenbits.formatter.lexer.LexerContext;
import it.sevenbits.formatter.lexer.token.Token;

public class BuildCommentFromBufferCommand implements ICommand {

    private final LexerContext lexer;

    public BuildCommentFromBufferCommand(final LexerContext lexer) {
        this.lexer = lexer;
    }

    @Override
    public void execute() {
        lexer.offer(new Token("LINE_COMMENT", lexer.getBuffer().toString()));
        lexer.getBuffer().setLength(0);
    }
}
