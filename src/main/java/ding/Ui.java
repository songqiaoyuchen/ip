package ding;

import java.util.Scanner;

/**
 * Handles all user interface interactions.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(Messages.LOGO);
        System.out.println(Messages.WELCOME);
        System.out.println(Messages.LINE_BREAK);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println("Ding: " + message);
    }

    public void showError(String errorMessage) {
        System.out.println("Ding: " + errorMessage);
    }

    public void showLineBreak() {
        System.out.println(Messages.LINE_BREAK);
    }

    public void close() {
        scanner.close();
    }
}
