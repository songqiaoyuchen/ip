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
    public static final String ERROR_EMPTY_INPUT = "Don't leave me hanging, friend! "
            + "Tell me what you'd like to do.";
    public static final String ERROR_EMPTY_COMMAND = "Oops! A command without words? "
            + "(That's like Pearl trying to trot without legs! Give me something to work with.)";
    public static final String ERROR_UNKNOWN_COMMAND = "I'm still learning your ways, friend. "
            + "(That command isn't in my Highland vocabulary yet!)";
    public static final String ERROR_MISSING_TASK_INDEX = "Which task catches your eye? Tell me the number, friend!";
    public static final String ERROR_INVALID_TASK_INDEX =
            "That task number doesn't match any in my notebook. The path gets steep sometimes - "
            + "(Let's try a number between 1 and your list size!)";
    public static final String ERROR_TODO_MISSING_DESCRIPTION =
            "A task without a name? Even Pearl has a purpose! What do you need to do?";
    public static final String ERROR_DEADLINE_MISSING_BY =
            "I'd love to add that deadline! Just use: deadline <description> /by <when>";
    public static final String ERROR_DEADLINE_MISSING_DESCRIPTION =
            "A deadline without a task? That's like saying 'the snow will melt' without saying when! "
            + "What's the deadline for?";
    public static final String ERROR_DEADLINE_MISSING_DATE =
            "When will this mountain be climbed? Tell me the /by date, friend!";
    public static final String ERROR_INVALID_DATE_TIME =
            "I couldn't understand that date/time. Try formats like 2019-12-02 1800 or 2/12/2019.";
    public static final String ERROR_EVENT_MISSING_RANGE =
            "Let me add that event for you! Just use: event <description> /from <start> /to <end>";
    public static final String ERROR_EVENT_MISSING_DESCRIPTION =
            "An event without a story? Even the smallest gathering in the Highlands has a tale! "
            + "What's this event about?";
    public static final String ERROR_EVENT_MISSING_TIME_RANGE =
            "When does this adventure happen? Use /from and /to to mark your trail through time!";
    public static final String ERROR_NOTHING_TO_UNDO =
            "The past is carved in stone, friend! Make a move first, then we can unwind the trail.";
    public static final String ERROR_NOTHING_TO_REDO =
            "The future is yet unwritten! Undo something first if you'd like to walk that path again.";

    // Error messages - Storage
    public static final String ERROR_STORAGE_SKIP_LINE =
            "(Line %d got corrupted like a worn old trail map! Skipping: %s)";
    public static final String ERROR_STORAGE_LOAD_FAILED =
            "The notebook pages scattered in the wind! Failed to load tasks: %s";
    public static final String ERROR_STORAGE_SAVE_FAILED =
            "Pearl knocked over the ink! Failed to save tasks: %s";
    public static final String ERROR_STORAGE_CREATE_DIR_FAILED =
            "Can't find the clearing for the notebook: %s";
    public static final String ERROR_STORAGE_MISSING_FIELDS =
            "This page in my notebook is missing pieces - some fields are blank!";
    public static final String ERROR_STORAGE_UNKNOWN_TASK_TYPE =
            "I found a strange marking in my notebook: %s - haven't seen this type of task before!";
    public static final String ERROR_STORAGE_DEADLINE_MISSING_BY =
            "A deadline without a due date? Even mountains have seasons when snow falls!";
    public static final String ERROR_STORAGE_EVENT_MISSING_RANGE =
            "An event with no start or end? That's like a journey without knowing when to leave!";
    public static final String ERROR_STORAGE_INVALID_DATE_TIME =
            "The date marks on this task are faded and unreadable: %s - can't decipher it!";
}