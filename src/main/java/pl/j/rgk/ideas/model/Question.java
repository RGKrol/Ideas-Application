package pl.j.rgk.ideas.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String name;
    private Category category;
    private List<Answer> answers;

    public Question() {
    }

    public Question(String questionName, Category category) {
        this.name = questionName;
        this.category = category;
        this.answers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question: '" + name + '\'' +
                ", category: '" + category +
                "', answers: " + answers.size();
    }
}
