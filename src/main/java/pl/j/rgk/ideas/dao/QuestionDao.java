package pl.j.rgk.ideas.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.j.rgk.ideas.model.Answer;
import pl.j.rgk.ideas.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class QuestionDao {

    private static final String PATH = "./questions.txt";

    private static final Logger LOG = Logger.getLogger(QuestionDao.class.getName());

    //do korzystania Jacksona korzystamy z:
    private final ObjectMapper objectMapper;  //służy do mapowania obiektu

    public QuestionDao() {
        this.objectMapper = new ObjectMapper();
    }

    //pobieranie pytań z pliku PATH w formie "jactson'a"
    private List<Question> getQuestions() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get(PATH)), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getQuestions", e);
            return new ArrayList<>();
        }
    }

    public List<Question> findAll() {
        return getQuestions();
    }

    public void add(Question question) {

        List<Question> questions = getQuestions();

        boolean repeat = false;
        for (Question q : questions) {
            if (Objects.equals(q.getName(), question.getName())) {
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            questions.add(question); //dodajemy pytanie do listy
        } else {
            LOG.log(Level.INFO, "This question exists. (To pytanie już istnieje.)");
        }

        saveQuestions(questions);
    }

    private void saveQuestions(List<Question> questions) {
        // objectMapper.writeValueAsString(questions); <-- zmiana listy na jackson'a
        try {
            Files.writeString(Paths.get(PATH), objectMapper.writeValueAsString(questions));
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on saveQuestion", e);
        }
    }

    public Optional<Question> findOne(String name) {
        return getQuestions().stream()         //korzystamy z metody na "strumieniu"
                .filter(c -> c.getName().equals(name))
                //filter() -> działa tak, że dostajemy po kolei wszystkie obiekty z listy i możemy je odfiltrować
                //tak aby zawierała tylko pasujące kategorie
                .findAny();     //nam potrzeba dowolny z wyników
    }

    public void addAnswer(Question question, Answer answer) {
        //pobierzemy wszystkie pytania -> odnajdziemy właściwe i je zmodyfikujemy
        List<Question> questions = getQuestions();

        for (Question q : questions) {
            if (Objects.equals(q.getName(), question.getName())) {

                boolean repeat = false;
                for (Answer a : q.getAnswers()) {
                    if (Objects.equals(a.getName(), answer.getName())) {
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    q.getAnswers().add(answer);
                } else {
                    LOG.log(Level.INFO, "This answer exists. (To pytanie już istnieje.)");
                }

            }
        }
        saveQuestions(questions);
    }

    public void del(Question question) {
        List<Question> questions = getQuestions();
        questions.remove(question);
        saveQuestions(questions);
    }
}
