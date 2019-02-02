package it.sevenbits.formatter.lexer.statemachine.command;

import it.sevenbits.formatter.lexer.LexerContext;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.LexemeMapper;
import it.sevenbits.formatter.lexer.token.Token;

public class BuildTokenFromLastCommand implements ICommand {

    private final LexerContext lexer;
    private LexemeMapper mapper;

    public BuildTokenFromLastCommand(final LexerContext lexer) {
        this.lexer = lexer;
        mapper = new LexemeMapper();
    }

    @Override
    public void execute() {
        char lexeme = (char) lexer.getLastSymbol();
        IToken token = new Token(mapper.getLexemeName(lexeme), String.valueOf(lexeme));
        lexer.offer(token);
    }
}
