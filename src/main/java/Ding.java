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

        // interactive loop
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
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
                System.out.println(taskManager.toString());
            // mark task as done or not done
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
                    Task task = taskManager.markTaskDone(taskIndex);
                    System.out.println("Ding: Nice! I've marked this task as done.");
                    System.out.println(task.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Ding: What is this? Please provide a valid task number.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ding: Uhhm, I can't find that task...");
                } 
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
                    Task task = taskManager.markTaskUndone(taskIndex);
                    System.out.println("Ding: OKOK, I've marked this task as not done yet.");
                    System.out.println(task.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Ding: What is this? Please provide a valid task number.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ding: Uhhm, I can't find that task...");
                } 
            // add new task
            } else if (userInput.toLowerCase().startsWith("todo ")) {
                taskManager.addTask(new Todo(userInput.substring(5).trim()));
                System.out.println("Ding: Got it. I've added this task to your to-do list.");
                System.out.println("Ding: You now have " 
                    + taskManager.getTaskCount() + " tasks in the list. So hardworking!");
            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                if (parts.length < 2) {
                    System.out.println("Ding: Oops! Please provide a deadline in the format: "
                        + "deadline <description> /by <time>");
                } else {
                    taskManager.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
                    System.out.println("Ding: Got it. I've added this task to your to-do list.");
                    System.out.println("Ding: You now have " 
                        + taskManager.getTaskCount() + " tasks in the list. So hardworking!");
                }
            } else if (userInput.toLowerCase().startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                if (parts.length < 3) {
                    System.out.println("Ding: Oops! Please provide an event in the format: "
                        + "event <description> /from <start time> /to <end time>");
                } else {
                    taskManager.addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                    System.out.println("Ding: Got it. I've added this task to your to-do list.");
                    System.out.println("Ding: You now have " 
                        + taskManager.getTaskCount() + " tasks in the list. So hardworking!");
                }
            } else {
                System.out.println("Ding: Sorry, I don't understand what you are trying to do :(");
            }
            System.out.println(LINE_BREAK);
        }
        scanner.close();
    }
}
