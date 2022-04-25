package pl.j.rgk.ideas;

import pl.j.rgk.ideas.handlers.*;
import pl.j.rgk.ideas.input.UserInputCommand;
import pl.j.rgk.ideas.input.UserInputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IdeasApplication {

    private static final Logger LOG = Logger.getLogger(IdeasApplication.class.getName());

    public static void main(String[] args) {
        new IdeasApplication().start();
    }

    private void start() {
        LOG.info("Start App ...");

        /*
         * category list -> categoryList(); //to ma uruchomić wskazaną metodę;
         * category add CategoryName    -> categoryAdd(CategoryName);
         *
         * quit -> quitApplication();
         *
         * question list -> questionList();
         * question add CategoryName QuestionName -> questionAdd (CategoryName, QuestionName);
         *
         * answer list QuestionName -> answerList(QuestionName);
         * answer add QuestionNme AnswerName -> answerAdd(QuestionName, AnswerAdd);
         *
         */

        UserInputManager userInputManager = new UserInputManager();

        //lista hendlerów każdy dla jednej komendy:
        List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new HelpCommandHandler()); //obsługa komendy help
        handlers.add(new QuitCommandHandler());
        handlers.add(new CategoryCommandHandler());
        handlers.add(new QuestionCommandHandler());
        handlers.add(new AnswerCommandHandler());


        boolean applicationLoop = true;
        while (applicationLoop) {
            try {
                UserInputCommand userInputCommand = userInputManager.nextCommand(); //zwraca komendę od użytkownika
                LOG.info(userInputCommand.toString());

                Optional<CommandHandler> currentHandler = Optional.empty();
                //łączenie komendy od użytkownika z hendlerami:
                for (CommandHandler handler : handlers) {
                    if (handler.supports(userInputCommand.getCommand())) {
                        currentHandler = Optional.of(handler);
                        break;
                    }
                }

                currentHandler
                        .orElseThrow(() -> new IllegalArgumentException("Unknown handler (Nierozpoznane polecenie): " + userInputCommand.getCommand()))
                        .handle(userInputCommand);
            } catch (QuitIdeasApplicationExceptions e) {
                LOG.info("Quit ...");
                applicationLoop = false;
            } catch (IllegalArgumentException e) {  //dla wszystkich IllegalA.... wspólny log
                LOG.log(Level.WARNING, "Validation exception (Wyjątek walidacji) " + e.getMessage());  //na końcu dodajemy samą wiadomość walidacyjną

            } catch (Exception e) {
                //e.printStackTrace();    //wyświetl na standardowym wyjściu
                LOG.log(Level.SEVERE, "Unknown error (Nieznany błąd)", e);
            }
        }
    }
}
