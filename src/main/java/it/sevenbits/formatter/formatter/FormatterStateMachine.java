package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.formatter.statemachine.FormatterState;
import it.sevenbits.formatter.formatter.statemachine.FormatterStateTransitions;
import it.sevenbits.formatter.formatter.statemachine.command.FCommandRepository;
import it.sevenbits.formatter.formatter.statemachine.command.IFCommand;
import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.io.interfaces.IWriter;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.LexerException;
import it.sevenbits.formatter.lexer.factory.ILexerFactory;
import it.sevenbits.formatter.lexer.token.IToken;

public class FormatterStateMachine implements IFormatter {

    private final ILexerFactory factory;
    private final FormatterContext context;
    private FormatterState currentState;
    private FormatterStateTransitions transitions;
    private FCommandRepository commandRepo;

    public FormatterStateMachine(final ILexerFactory factory) {
        this.factory = factory;
        context = new FormatterContext();
        transitions = new FormatterStateTransitions();
        currentState = transitions.getStartState();
        commandRepo = new FCommandRepository(context);
    }

    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        ILexer lexer = factory.createLexer(reader);
        context.setWriter(writer);

        while (lexer.hasToken()) {
            try {
                IToken token = lexer.getToken();
                context.setLastToken(token);
                IFCommand command = commandRepo.getCommand(currentState, token);
                command.execute();
                currentState = transitions.nextState(currentState, token.getName());
            } catch (LexerException e) {
                throw new FormatterException("lexer error", e);
            }
        }
    }
}
