package pl.j.rgk.ideas;

import java.util.Objects;

public enum Action {
    LIST("list"), ADD("add"), DEL("del"),
    HELP("help"), QUITE("quite"), CATEGORY("category"), QUESTION("question"), ANSWER("answer");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    //zwraca np. ADD gdy podano add,
    //enum ma domyślne metodę która zwraca wartości LIST,ADD itd to "values()"
    public static Action of(String value) {
        for (Action action : values()) {        //values() to metoda w każdym enumie która zwraca wszystkie wartości enuma w postaci tablicy.
            if (Objects.equals(action.value, value)) {
                return action;
            }
        }

        throw new IllegalArgumentException("Unknown action (Nieznana akcja)" + value);
    }
}