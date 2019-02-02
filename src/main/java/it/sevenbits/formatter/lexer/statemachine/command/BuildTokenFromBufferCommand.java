package it.sevenbits.formatter.lexer.statemachine.command;

import it.sevenbits.formatter.lexer.LexerContext;
import it.sevenbits.formatter.lexer.token.Token;

public class BuildTokenFromBufferCommand implements ICommand {

    private final LexerContext lexer;

    public BuildTokenFromBufferCommand(final LexerContext lexer) {
        this.lexer = lexer;
    }

    @Override
    public void execute() {
        lexer.offer(new Token("WORD", lexer.getBuffer().toString()));
        lexer.getBuffer().setLength(0);
    }
}
