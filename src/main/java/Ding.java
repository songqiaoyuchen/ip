import java.util.Scanner;

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
        String LINE_BREAK = "----------------------------------";
        System.out.println(LINE_BREAK);

        // interactive echo loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Ding: Goodbye! We will meet again soon.");
                break;
            } else if (userInput.trim().isEmpty()) {
                System.out.println("Ding: Say something and I will repeat after you.");
            } else {
                System.out.println("Ding: " + userInput);
            }
            System.out.println(LINE_BREAK);
        }
        scanner.close();
    }
}
