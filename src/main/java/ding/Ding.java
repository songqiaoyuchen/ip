package ding;
import java.util.Scanner;

import ding.commands.Command;
import ding.exceptions.DingException;

public class Ding {
    public static void main(String[] args) {
        String logo = " ____  _ \n"
                + "|  _ \\(_)_ __   __ _ \n"
                + "| | | | | '_ \\ / _` |\n"
                + "| |_| | | | | | (_| |\n"
                + "|____/|_|_| |_|\\__, |\n"
                + "               |___/ \n";
        System.out.println(logo);
        // greetings
        System.out.println("Hi! My name is Ding. I'm 20 years old :)");
        System.out.println("I am still learning...");

        // line break delimiter
        String LINE_BREAK = "----------------------------------------";
        System.out.println(LINE_BREAK);

        // interactive loop
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        Parser parser = new Parser();
        while (true) {
            try {
                String userInput = scanner.nextLine();
                Command command = parser.parse(userInput);
                System.out.println(command.execute(taskManager));
                if (command.isExit()) {
                    break;
                }
            } catch (DingException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(LINE_BREAK);
            }
        }
        scanner.close();
    }
}
