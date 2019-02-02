package it.sevenbits.formatter.formatter.statemachine.command;

import it.sevenbits.formatter.formatter.FormatterContext;
import it.sevenbits.formatter.io.exceptions.WriteException;
import it.sevenbits.formatter.io.interfaces.IWriter;

import java.io.IOException;

public class WriteTokenCommand implements IFCommand {

    private FormatterContext context;

    public WriteTokenCommand(final FormatterContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        String lexeme = context.getLastToken().getLexeme();
        IWriter writer = context.getWriter();

        try {
            for (int i = 0; i < lexeme.length(); i++) {
                writer.write(lexeme.charAt(i));
            }
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

}
