package pl.j.rgk.ideas.model;

public class Category {
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Category: '" + name + '\'';
    }
}
