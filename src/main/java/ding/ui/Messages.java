package ding.ui;

/**
 * Contains all user-facing messages used in the Ding application.
 */
public class Messages {
    // UI Constants
    public static final String LOGO = " ____  _ \n"
            + "|  _ \\(_)_ __   __ _ \n"
            + "| | | | | '_ \\ / _` |\n"
            + "| |_| | | | | | (_| |\n"
            + "|____/|_|_| |_|\\__, |\n"
            + "               |___/ \n";
    public static final String LINE_BREAK = "=".repeat(40);
    
    // Welcome messages
    public static final String WELCOME = "Hi! I'm Ding from the highlands. I'm 20 years young :)\n"
            + "Welcome to my world! Still learning English, just like my horse Pearl learning to trot!";
    
    // Exit message
    public static final String GOODBYE = "Goodbye, friends! We'll meet again when the snow melts on the mountains.\n"
            + "Remember: The world is vast, and there's much to explore!";
    
    // Task added messages
    public static final String TASK_ADDED = "Got it! Task has been added to my little notebook:\n%s";
    public static final String TASK_COUNT = "You now have %d tasks. Keep grinding, worker!\n"
            + "(Every step forward counts, even on the steepest trail!)";
    
    // Task status messages
    public static final String TASK_MARKED_DONE = "Excellent work! Task marked as completed:\n%s\n"
            + "(Mission accomplished! Time for some sweet tea reward!)";
    public static final String TASK_MARKED_UNDONE = "Noted, I've returned this task to 'pending':\n%s\n"
            + "(Slow and steady wins the race!)";
    
    // Task deletion messages
    public static final String TASK_DELETED = "Deleted, erased, gone forever! Removed this task:\n%s";
    public static final String TASK_COUNT_AFTER_DELETE = "Now down to %d tasks. \n"
            + "(Hard work pays off, even when you're deleting things!)";
    
    // List messages
    public static final String TASK_LIST_HEADER = "Here's your current task list:\n\n%s";
    public static final String EMPTY_LIST = "Your task list is as empty as a peaceful grassland!\n"
        + "(Enjoy the freedom while it lasts!)";
    public static final String FIND_RESULTS = "Here are the matching tasks in your list:\n%s";
    public static final String FIND_NO_MATCHES = "I couldn't find any tasks matching that keyword.";
    
    // Error messages - TaskManager
    public static final String ERROR_TASK_NOT_FOUND = "Hmm, I can't seem to find that task...\n"
            + "(Maybe it wandered off like a free-spirited horse?)";
    public static final String ERROR_TASK_ALREADY_DONE = "This task is already completed, friend!\n"
            + "(No need to mark it twice - your work here is done!)";
    public static final String ERROR_TASK_ALREADY_UNDONE = "This task is already in the 'to-do' pile.\n"
            + "(Patience, my friend - let the bullet fly a little longer!)";
    public static final String ERROR_LOAD_TASKS = "I couldn't load your tasks from: %s\n"
            + "(Don't worry, we can start fresh! Even the clearest lake starts with a single drop.)";
}