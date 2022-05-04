package pl.j.rgk.ideas.input;

import pl.j.rgk.ideas.Action;

import java.util.ArrayList;
import java.util.List;

public class UserInputCommand {

    private String command;

    private Action action;

    private List<String> param;

    /**
     * category list -> categoryList(); //to ma uruchomić wskazaną metodę;
     * category add CategoryName    -> categoryAdd(CategoryName);
     * <p>
     * quit -> quitApplication();
     * <p>
     * [answer, add, QuestionNme, AnswerName]
     */

    public UserInputCommand(String line) {
        if (line != null) {
            //line.split("\\s");  <--- rozbicie stringa po białych znakach na tablicę stringów
            String[] array = line.split("\\s");
            if (array.length > 0) {
                command = array[0];
            }
            if (array.length > 1) {
                action = Action.of(array[1]);   //.of() zmienia stringa w enuma
            }

            param = new ArrayList<>();
            for (int i = 2; i < array.length; i++) {
                param.add(array[i]);
            }
        }
    }

    public String getCommand() {
        return command;
    }

    public Action getAction() {
        return action;
    }

    public List<String> getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "UserInputCommand{" +
                "command='" + command + '\'' +
                ", action='" + action + '\'' +
                ", param=" + param +
                '}';
    }
}
