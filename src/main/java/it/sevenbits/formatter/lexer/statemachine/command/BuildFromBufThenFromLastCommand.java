package it.sevenbits.formatter.lexer.statemachine.command;

import it.sevenbits.formatter.lexer.LexerContext;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.LexemeMapper;
import it.sevenbits.formatter.lexer.token.Token;

public class BuildFromBufThenFromLastCommand implements ICommand {

    private final LexerContext lexer;
    private final LexemeMapper mapper;

    public BuildFromBufThenFromLastCommand(final LexerContext lexer) {
        this.lexer = lexer;
        mapper = new LexemeMapper();
    }

    @Override
    public void execute() {
        IToken first = new Token("WORD", lexer.getBuffer().toString());
        lexer.getBuffer().setLength(0);
        lexer.offer(first);
        char lexeme = (char) lexer.getLastSymbol();
        IToken second = new Token(mapper.getLexemeName(lexeme), String.valueOf(lexeme));
        lexer.offer(second);
    }
}
