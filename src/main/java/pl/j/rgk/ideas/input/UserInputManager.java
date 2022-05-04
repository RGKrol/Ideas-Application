package pl.j.rgk.ideas.input;

import java.util.Scanner;

public class UserInputManager {

    private final Scanner scanner;

    public UserInputManager() {
        scanner = new Scanner(System.in);
    }

    public UserInputCommand nextCommand() {
        return new UserInputCommand(scanner.nextLine());    //zwracamy to co nam "odda" konstruktor z klasy UserInputCommand - czyli prawidłowo przygotowane polecenie
    }
}
