package pl.j.rgk.ideas.handlers;

import pl.j.rgk.ideas.dao.CategoryDao;
import pl.j.rgk.ideas.dao.QuestionDao;
import pl.j.rgk.ideas.input.UserInputCommand;
import pl.j.rgk.ideas.model.Answer;
import pl.j.rgk.ideas.model.Question;

import java.util.logging.Logger;

public class AnswerCommandHandler extends BaseCommandHandler {

    private static final String COMMAND_NAME = "answer";

    private static final Logger LOG = Logger.getLogger(AnswerCommandHandler.class.getName());

    private final QuestionDao questionDao;
    private final CategoryDao categoryDao;

    public AnswerCommandHandler() {
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
                LOG.info("List of answers (Lista odpowiedzi) ...");
                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("Wrong command format. Check help for more information. (Zły format polecenia. Sprawdź 'help' aby uzyskać więcej informacji)");
                }
                String questionName = command.getParam().get(0);
                Question question = questionDao.findOne(questionName)
                        .orElseThrow(() -> new IllegalArgumentException("Question not found " + questionName));
                //po tym poleceniu pod question jest już wskazane pytanie

                displayQuestion(question);//wyświetlenie odpowiedzi dla wskazanego pytania
            }
            case ADD -> {
                // answer add QuestionName AnswerName
                LOG.info(String.format("Add answer in question %s (Dodawanie odpowiedzi dla pytania %s) ...", command.getParam().get(0), command.getParam().get(0)));
                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("Wrong command format. Check help for more information. (Zły format polecenia. Sprawdź help aby uzyskać więcej informacji.)");
                }
                String questionName = command.getParam().get(0);
                String answerName = command.getParam().get(1);

                //pobieramy pytanie do którego dodajemy odpowiedź
                Question question = questionDao.findOne(questionName)
                        .orElseThrow(() -> new IllegalArgumentException("Question not found " + questionName));
                questionDao.addAnswer(question, new Answer(answerName));
            }
            case DEL -> {
                String answerName   = command.getParam().get(1);
                String questionName = command.getParam().get(0);

                LOG.info(String.format("Deleting answer %s in question %s. (Usuwanie odpowiedzi %s z pytania %s.)", answerName, questionName, answerName, questionName));

                Question question = questionDao.findOne(questionName)
                        .orElseThrow(()-> new IllegalArgumentException("Question not found (Pytanie nie znalezione): " + questionName));
                questionDao.delAnswer(question, new Answer(answerName));
            }

            default -> {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s (Nieznana akcja: %s dla komendy: %s)",
                        command.getAction(), command.getCommand(), command.getAction(), command.getCommand()));
            }
        }
    }

    private void displayQuestion(Question question) {
        System.out.println("Question - " + question.getName() + ':');
        question.getAnswers().forEach(System.out::println);
    }
}
