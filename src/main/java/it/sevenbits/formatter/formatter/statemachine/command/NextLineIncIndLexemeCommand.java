package it.sevenbits.formatter.formatter.statemachine.command;

import it.sevenbits.formatter.formatter.FormatterContext;
import it.sevenbits.formatter.io.exceptions.WriteException;
import it.sevenbits.formatter.io.interfaces.IWriter;

import java.io.IOException;

public class NextLineIncIndLexemeCommand implements IFCommand {

    private FormatterContext context;

    public NextLineIncIndLexemeCommand(final FormatterContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        String lexeme = context.getLastToken().getLexeme();
        IWriter writer = context.getWriter();

        try {
            writer.write('\n');
            context.incrementIndentationCount();
            for (int i = 0; i < context.getIndentationCount() * getTimes(); i++) {
                writer.write(' ');
            }
            for (int i = 0; i < lexeme.length(); i++) {
                writer.write(lexeme.charAt(i));
            }
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    private int getTimes() {
        return (int) Math.pow(2, 2);
    }
}
