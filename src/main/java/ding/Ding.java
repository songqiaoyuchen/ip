package ding;
import java.util.ArrayList;
import java.util.List;

import ding.commands.Command;
import ding.exceptions.DingException;
import ding.tasks.Task;

/**
 * The main Ding class that initializes the application components and processes user input.
 */
public class Ding {
    private final TaskManager taskManager;
    private final Parser parser;
    private final List<String> startupWarnings;

    /**
     * Constructs a Ding instance, initializing storage, UI, parser, and task manager.
     * @throws DingException if an error occurs while loading tasks from storage
     */
    public Ding() throws DingException {
        Storage storage = new Storage();
        this.parser = new Parser();
        ArrayList<String> warnings = new ArrayList<>();
        ArrayList<Task> initialTasks = storage.load(warnings);
        this.startupWarnings = warnings;
        this.taskManager = new TaskManager(storage, initialTasks);
    }

    /**
     * Returns non-fatal warnings collected during startup.
     */
    public List<String> getStartupWarnings() {
        return new ArrayList<>(startupWarnings);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(taskManager);
        } catch (DingException e) {
            return e.getMessage();
        }
    }

}
