package pl.j.rgk.ideas.model;

import java.util.Objects;

public class Answer {
    private String name;

    public Answer() {
    }

    public Answer(String answerName) {
        this.name = answerName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Answer: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return name.equals(answer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
