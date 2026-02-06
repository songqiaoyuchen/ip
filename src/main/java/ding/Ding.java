package ding;
import java.util.ArrayList;

import ding.commands.Command;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;

/**
 */
public class Ding {
    private final TaskManager taskManager;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructs a Ding instance, initializing storage, UI, parser, and task manager.
     * @throws DingException if an error occurs while loading tasks from storage
     */
    public Ding() throws DingException {
        this.storage = new Storage();
        this.parser = new Parser();
        ArrayList<Task> initialTasks = new ArrayList<>();
        initialTasks = storage.load();
        this.taskManager = new TaskManager(storage, initialTasks);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(taskManager);
        } catch (DingException e) {
            return String.format(Messages.ERROR_LOAD_TASKS, e.getMessage());
        }
    }

}
