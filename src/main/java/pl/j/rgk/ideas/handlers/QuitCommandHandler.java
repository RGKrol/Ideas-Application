package pl.j.rgk.ideas.handlers;

import pl.j.rgk.ideas.QuitIdeasApplicationExceptions;
import pl.j.rgk.ideas.input.UserInputCommand;

public class QuitCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "quit";

    @Override
    public void handle(UserInputCommand command) {
        // rzucamy "naszym" wyjątkiem aby wyjść z obecnego kontekstu
        throw new QuitIdeasApplicationExceptions();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}