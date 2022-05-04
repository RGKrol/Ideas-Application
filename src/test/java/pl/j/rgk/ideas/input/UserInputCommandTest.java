package pl.j.rgk.ideas.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserInputCommandTest {

    @Test
        //test "powinien zbudować prawidłowe polecenie wprowadzania danych"
    void shouldBuildCorrectUserInputCommand() {
        // given <-- określamy co mamy na wejściu, to przygotowanie środowiska
        String input = "category add CategoryName";

        // when <-- logika do przetestowania
        UserInputCommand userInputCommand = new UserInputCommand(input);

        // then <-- assercje, czyli weryfikacja
        Assertions.assertEquals("category", userInputCommand.getCommand());
        Assertions.assertEquals("add", userInputCommand.getAction());
        Assertions.assertLinesMatch(List.of("CategoryName"), userInputCommand.getParam());
    }

    @Test
    void shouldBuildCorrectUserInputCommandWithMultipleParams() {
        // given
        String input = "command action param1 param2 param3";

        // when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        // then
        Assertions.assertEquals("command", userInputCommand.getCommand());
        Assertions.assertEquals("action", userInputCommand.getAction());
        Assertions.assertLinesMatch(List.of("param1", "param2", "param3"), userInputCommand.getParam());
    }

    @Test
    void shouldBuildCorrectUserInputCommandWithoutParams() {
        // given
        String input = "command action";

        // when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        // then
        Assertions.assertEquals("command", userInputCommand.getCommand());
        Assertions.assertEquals("action", userInputCommand.getAction());
        Assertions.assertEquals(0, userInputCommand.getParam().size());
    }

    @Test
    void shouldBuildCorrectUserInputCommandOnlyCommand() {
        // given
        String input = "command";

        // when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        // then
        Assertions.assertEquals("command", userInputCommand.getCommand());
        Assertions.assertNull(userInputCommand.getAction());
        Assertions.assertEquals(0, userInputCommand.getParam().size());
    }
}