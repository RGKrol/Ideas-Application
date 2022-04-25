package pl.j.rgk.ideas.handlers;

import pl.j.rgk.ideas.input.UserInputCommand;

public interface CommandHandler {
    // ma obsłużyć komendę
    void handle(UserInputCommand command);

    // ma podjąć decyzję czy obsługuje daną komendę
    boolean supports(String command);
}
