import java.util.ArrayList;
import java.util.Scanner;

public class Ding {
    private static ArrayList<String> tasks = new ArrayList<>();
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
                System.out.println("Ding: Add some tasks to your to-do list, and I will remember it for you.");
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Ding: Here are the tasks you have added:");
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) != null) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else {
                tasks.add(userInput);
                System.out.println("Ding: Got it. I've added this task to your to-do list.");
            }
            System.out.println(LINE_BREAK);
        }
        scanner.close();
    }
}
