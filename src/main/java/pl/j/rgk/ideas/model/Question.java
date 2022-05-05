package pl.j.rgk.ideas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void setName(String name) {
        this.name = name;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question: '" + name + '\'' +
                ", category: '" + category +
                "', answers: " + answers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return name.equals(question.name) && category.equals(question.category) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, answers);
    }
}
