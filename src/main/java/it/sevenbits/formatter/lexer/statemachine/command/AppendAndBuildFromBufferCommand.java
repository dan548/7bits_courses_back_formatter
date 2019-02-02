package it.sevenbits.formatter.lexer.statemachine.command;

import it.sevenbits.formatter.lexer.LexerContext;
import it.sevenbits.formatter.lexer.token.Token;

public class AppendAndBuildFromBufferCommand implements ICommand {

    private final LexerContext lexer;

    public AppendAndBuildFromBufferCommand(final LexerContext lexer) {
        this.lexer = lexer;
    }

    @Override
    public void execute() {
        StringBuilder buf = lexer.getBuffer();
        buf.append((char) lexer.getLastSymbol());
        lexer.offer(new Token("LITERAL", buf.toString()));
        buf.setLength(0);
    }
}
