package pl.j.rgk.ideas.model;

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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "name='" + name + '\'' +
                '}';
    }
}
