package pl.j.rgk.ideas.handlers;

import pl.j.rgk.ideas.input.UserInputCommand;

import java.util.logging.Logger;

public class HelpCommandHandler extends BaseCommandHandler {

    private static Logger LOG = Logger.getLogger(HelpCommandHandler.class.getName());

    public static final String COMMAND_NAME = "help";

    @Override
    public void handle(UserInputCommand command) {

        if (command.getAction() == null) {
            System.out.println("Help (Pomoc)");
            System.out.println("The application stores a database of answers to questions sorted into unique categories.");
            System.out.println("(Aplikacja przechowuje bazę odpowiedzi na pytania posegregowane na niepowtarzalne kategorie.)\n");
            System.out.println("Allowed command (obsługiwane komendy): help, quite, category, question, answer");
            System.out.println("Command pattern (konstrukcja polecenia): <command> <action> <param1> <param2>");
            System.out.println("Example (przykład): category add CategoryName");
            System.out.println("Issue the command: help <command>, for more information. (Wydaj polecenie: help <command>, aby uzyskać dodatkowe informacje.)");
        } else if (command.getParam().isEmpty()) {

            switch (command.getAction()) {
                case HELP:
                    LOG.info("Help for (Pomoc dla): <help>");
                    System.out.println("The <help> command shows some basic help. (Komenda <help> wyświetla podstawową pomoc.)");
                    System.out.println("The <help help> command displays this help. (Komenda <help help> wyświetla tę pomoc.)");
                    System.out.println("\nAuthor: Robert G. Król, contact (kontakt): rgkrol@gmail.com\n");
                    System.out.println("This is my first Java program. Please bear with me and contact me about any shortcomings.");
                    System.out.println("(To mój pierwszy program w Java. Proszę o wyrozumiałość i o kontakt w sprawie niedociągnięć.)");
                    break;

                case QUITE:
                    LOG.info("Help for (Pomoc dla): <quite>");
                    System.out.println("Command <quite> closes the program. (Polecenie <quite> zamyka program.)");
                    break;

                case CATEGORY:
                    LOG.info("Help for (Pomoc dla): <category>");
                    System.out.println("The <category> command supports categories. (Polecenie <category> obsługuje kategorie.)");
                    System.out.println("Supported Actions (Obsługiwane akcje): list, add, del");
                    System.out.println();
                    System.out.println("list: displays the available categories. (wyświetla dostępne kategorie.)");
                    System.out.println();
                    System.out.println("add: adds new categories. (dodaje nową kategorie.)");
                    System.out.println("Example (Przykład): category add CategoryName");
                    System.out.println();
                    System.out.println("del: deletes the selected category. (usuwa wskazaną kategorię.)");
                    System.out.println("Example (Przykład): category del DeletedCategory");
                    break;

                case QUESTION:
                    LOG.info("Help for (Pomoc dla): <question> ");
                    System.out.println("The <question> command supports questions. (Polecenie <question> obsługuje pytania.)");
                    System.out.println("Questions are always assigned to one category. (Pytania są zawsze przypisane do jednej kategorii.)");
                    System.out.println("Deleting a category removes its assigned questions. (Usunięcie kategorii, usuwa przypisane jej pytania.)");
                    System.out.println("Removing a question removes the answers assigned to it. (Usunięcie pytania, usuwa przypisane doń odpowiedzi.) ");
                    System.out.println();
                    System.out.println("Supported actions (Obsługiwane akcje): list, add, del");
                    System.out.println();
                    System.out.println("list: displays the available questions. (wyświetla dostępne pytania.) ");
                    System.out.println();
                    System.out.println("add: adds new questions. (dodaje nowe pytanie.)");
                    System.out.println("Each question always has exactly one category assigned to it, which is indicated when creating the question.");
                    System.out.println("(Każde pytanie ma zawsze przypisane dokładnie jedną kategorię, która jest wskazywana przy tworzeniu pytania.)");
                    System.out.println("Example (Przykład): question add Category NewQuestion");
                    System.out.println();
                    System.out.println("del: deletes the indicated question and the answers to it (kasuje wskazane pytanie i odpowiadające na nie odpowiedzi)");
                    System.out.println("Example (Przykład): question del DeletedQuestion");
                    break;

                case ANSWER:
                    LOG.info("Help for (Pomoc dla): <answer>");
                    System.out.println("The <answer> command supports answers. (Polecenie <answer> obsługuje odpowiedzi.)");
                    System.out.println("The answer is associated with a specific question. (Odpowiedź jest przypisana do konkretnego pytania.)");
                    System.out.println("Deleting a question deletes the answers assigned to it. (Usunięcie pytania kasuje przypisane do niego odpowiedzi.)");
                    System.out.println("Deleting all answers to a given question will not delete the question.");
                    System.out.println("(Usunięcie wszystkich odpowiedzi do danego pytanie, nie usunie pytania.)");
                    System.out.println();
                    System.out.println("Supported actions (Obsługiwane akcje): list, add, del");
                    System.out.println();
                    System.out.println("list: displays the available answers to the selected question (wyświetla dostępne odpowiedzi do wskazanego pytania)");
                    System.out.println("Example (Przykład): answer list Question");
                    System.out.println();
                    System.out.println("add: adds new answer (dodaje nową odpowiedź)");
                    System.out.println("Example (Przykład): answer add Question NewAnswer");
                    System.out.println();
                    System.out.println("del: deletes the answer to the selected question (kasuje odpowiedź do wskazanego pytania)");
                    System.out.println("Example (Przykład): answer del Question DeletedAnswer");
                    break;

                default:
                    throw new IllegalArgumentException(String.format("Unknown action (Nieznana akcja): %s from command (dla komendy): %s",
                            command.getAction(), command.getCommand()));
            }

        } else {
            throw new IllegalArgumentException(String.format("Incorrect command format, see \"help\". (Nieprawidłowy format polecenia, sprawdź \"help\")."));
        }
    }


    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
