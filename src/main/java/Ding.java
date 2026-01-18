import java.util.ArrayList;
import java.util.Scanner;

public class Ding {
    private static ArrayList<Task> tasks = new ArrayList<>();
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
            // exit case
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Ding: Goodbye! We will meet again soon.");
                break;
            // empty input
            } else if (userInput.trim().isEmpty()) {
                System.out.println("Ding: Add some tasks to your to-do list, " 
                    + "and I will remember it for you.");
            // list tasks 
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Ding: Here are the tasks you have added:");
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) != null) {
                        Task task = tasks.get(i);
                        String taskNumber = (i + 1) + ". ";
                        System.out.println(taskNumber + task.toString());
                    }
                }
            // mark task as done or not done
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size() 
                            && tasks.get(taskIndex) != null && !tasks.get(taskIndex).isDone()) {
                        tasks.get(taskIndex).toggleDone();
                        System.out.println("Ding: Ok, good job on completing this one :)");
                        System.out.println(tasks.get(taskIndex).toString());
                    } else {
                        System.out.println("Ding: I don't get it. " 
                            + "This task either don't exist or is already marked as done.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ding: What is this? Please provide a valid task number.");
                }
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size() 
                            && tasks.get(taskIndex) != null && tasks.get(taskIndex).isDone()) {
                        tasks.get(taskIndex).toggleDone();
                        System.out.println("Ding: OKOK, I've marked this task as not done yet.");
                        System.out.println(tasks.get(taskIndex).toString());
                    } else {
                        System.out.println("Ding: I don't get it. " 
                            + "This task either don't exist or is already marked as not done.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ding: What is this? Please provide a valid task number.");
                }
            // add new task
            } else {
                tasks.add(new Task(userInput));
                System.out.println("Ding: Got it. I've added this task to your to-do list.");
            }
            System.out.println(LINE_BREAK);
        }
        scanner.close();
    }
}
