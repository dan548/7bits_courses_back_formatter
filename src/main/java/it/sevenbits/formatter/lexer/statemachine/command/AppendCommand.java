package it.sevenbits.formatter.lexer.statemachine.command;

import it.sevenbits.formatter.lexer.LexerContext;

public class AppendCommand implements ICommand {

    private LexerContext context;

    public AppendCommand(final LexerContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        context.getBuffer().append((char) context.getLastSymbol());
    }
}
