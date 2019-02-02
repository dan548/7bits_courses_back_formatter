package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.io.exceptions.ReadException;
import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.lexer.statemachine.LexerState;
import it.sevenbits.formatter.lexer.statemachine.LexerStateTransitions;
import it.sevenbits.formatter.lexer.statemachine.command.CommandRepository;
import it.sevenbits.formatter.lexer.token.IToken;

import java.io.IOException;

public class LexerStateMachine implements ILexer {

    private IReader reader;

    private LexerState currentState;
    private LexerStateTransitions stateTransitions;
    private CommandRepository commandRepo;
    private LexerContext context;

    private boolean isHasToken;
    private int bufChar;

    private boolean isStarted;

    public LexerStateMachine(final IReader reader) {
        this.reader = reader;
        context = new LexerContext();
        stateTransitions = new LexerStateTransitions();
        currentState = stateTransitions.getStartState();
        commandRepo = new CommandRepository(context);
        isStarted = false;
        isHasToken = false;
    }

    @Override
    public boolean hasToken() {
        if (isQueueNotEmpty()) {
            return true;
        } else {
            try {
                do {
                    bufChar = reader.read();
                } while (bufChar == ' ' || bufChar == '\n');
            } catch (IOException | ReadException e) {
                return false;
            }
        }
        isHasToken = true;
        return true;
    }

    @Override
    public IToken getToken() throws LexerException {
        if (isQueueNotEmpty()) {
            return context.poll();
        }
        int symbol;
        try {
            while (!currentState.toString().equals("NO_TOKEN") || !isStarted) {
                isStarted = true;
                if (isHasToken) {
                    symbol = bufChar;
                } else {
                    symbol = reader.read();
                }
                context.setLastSymbol(symbol);
                commandRepo.getCommand(currentState, (char) symbol).execute();
                currentState = stateTransitions.nextState(currentState, symbol);
                isHasToken = false;
            }
        } catch (ReadException | IOException e) {
            throw new LexerException("reading error");
        }
        if (isQueueNotEmpty()) {
            isStarted = false;
            isHasToken = false;
            return context.poll();
        } else {
            throw new LexerException("No tokens left!");
        }
    }

    private boolean isQueueNotEmpty() {
        return context.getTokenQueue().peek() != null;
    }
}
