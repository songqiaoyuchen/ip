package ding;

/**
 * Contains all user-facing messages used by the application.
 */
public class Messages {
    // UI Constants
    public static final String LOGO = " ____  _ \n"
            + "|  _ \\(_)_ __   __ _ \n"
            + "| | | | | '_ \\ / _` |\n"
            + "| |_| | | | | | (_| |\n"
            + "|____/|_|_| |_|\\__, |\n"
            + "               |___/ \n";
    public static final String LINE_BREAK = "----------------------------------------";
    
    // Welcome messages
    public static final String WELCOME = "Hi! My name is Ding. I'm 20 years old :)\nI am still learning...";
    
    // Exit message
    public static final String GOODBYE = "Goodbye! We will meet again soon.";
    
    // Task added messages
    public static final String TASK_ADDED = "Got it. I've added this task to your to-do list.\n%s";
    public static final String TASK_COUNT = "You now have %d tasks in the list. So hardworking!";
    
    // Task status messages
    public static final String TASK_MARKED_DONE = "Nice! I've marked this task as done:\n%s";
    public static final String TASK_MARKED_UNDONE = "OK, I've marked this task as not done yet:\n%s";
    
    // Task deletion messages
    public static final String TASK_DELETED = "Noted. I've removed this task:\n%s";
    public static final String TASK_COUNT_AFTER_DELETE = "You now have %d tasks in the list. Keep going!";
    
    // List messages
    public static final String TASK_LIST_HEADER = "Here are the tasks you have added:\n\n%s";
    
    // Error messages - TaskManager
    public static final String ERROR_TASK_NOT_FOUND = "Uhhm, I can't find that task...";
    public static final String ERROR_TASK_ALREADY_DONE = "Ha! This task is already marked as done."
            + "Don't forget your hard work earlier :)";
    public static final String ERROR_TASK_ALREADY_UNDONE = "This task is already marked as not done."
            + "Let's get it done soon :)";
    
    // Error messages - Storage
    public static final String ERROR_LOAD_TASKS = "I can't seem to find the tasks saved: %s";
}
