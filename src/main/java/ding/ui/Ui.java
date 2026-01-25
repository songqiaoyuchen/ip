package ding.ui;

import java.util.Scanner;

/**
 * Handles all user interface interactions.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object with a scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo to the user.
     */
    public void showWelcome() {
        System.out.println(Messages.LOGO);
        System.out.println(Messages.WELCOME);
        System.out.println(Messages.LINE_BREAK);
    }

    /**
     * Reads a command line from the user.
     *
     * @return the user's input command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println("Ding: " + message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage the error message to display
     */
    public void showError(String errorMessage) {
        System.out.println("Ding: " + errorMessage);
    }

    /**
     * Displays a line break for visual separation.
     */
    public void showLineBreak() {
        System.out.println(Messages.LINE_BREAK);
    }

    /**
     * Closes the scanner and releases resources.
     */
    public void close() {
        scanner.close();
    }
}
