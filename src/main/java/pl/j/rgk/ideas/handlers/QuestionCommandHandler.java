package pl.j.rgk.ideas.handlers;

import pl.j.rgk.ideas.dao.CategoryDao;
import pl.j.rgk.ideas.dao.QuestionDao;
import pl.j.rgk.ideas.input.UserInputCommand;
import pl.j.rgk.ideas.model.Category;
import pl.j.rgk.ideas.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class QuestionCommandHandler extends BaseCommandHandler {

    private static final String COMMAND_NAME = "question";

    private static final Logger LOG = Logger.getLogger(QuestionCommandHandler.class.getName());

    private final QuestionDao questionDao;
    private final CategoryDao categoryDao;

    public QuestionCommandHandler() {
        questionDao = new QuestionDao();
        categoryDao = new CategoryDao();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void handle(UserInputCommand command) {

        if (command.getAction() == null) {
            throw new IllegalArgumentException("action can't be null (akcja musi być podana)");
        }

        switch (command.getAction()) {
            case LIST -> {
                LOG.info("List of questions (Lista pytań) ...");
                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("category list doesn't support any additional params (polecenie 'question' list nie wspiera dodatkowych parametrów)");
                }
                List<Question> questions = questionDao.findAll();
                questions.forEach(System.out::println);
            }
            case ADD -> {
                // question add CategoryName QuestionName
                LOG.info(String.format("Add question in category %s (Dodawanie pytania w kategorii %s) ...", command.getParam().get(0), command.getParam().get(0)));
                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("Wrong command format. Check help for more information. (Zły format polecenia. Sprawdź help aby uzyskać więcej informacji.)");
                }
                String categoryName = command.getParam().get(0);
                String questionName = command.getParam().get(1);
                Category category = categoryDao.findOne(categoryName)   //zwraca optionala jeżeli znajdzie kategorie
                        .orElseThrow(() -> new IllegalArgumentException("Category not found: (Kategoria nie znaleziona): " + categoryName));

                //dodajemy pytanie
                questionDao.add(new Question(questionName, category));
                System.out.println(" ");
            }
            case DEL -> {
                LOG.info("Deleting question (Usuwanie pytania)");
                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("Wrong command format. Check help for more information. (Zły format polecenia. Sprawdź help aby uzyskać więcej informacji.)");
                }
                String categoryName = command.getParam().get(0);
                String questionName = command.getParam().get(1);
                Category category = categoryDao.findOne(categoryName)
                        .orElseThrow(() -> new IllegalArgumentException("Category not found: (Kategoria nie znaleziona): " + categoryName));

                questionDao.del(new Question(questionName, category));
            }
            default -> {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s (Nieznana akcja: %s dla komendy: %s)",
                        command.getAction(), command.getCommand(), command.getAction(), command.getCommand()));
            }
        }
    }
}
