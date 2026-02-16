package ding;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import ding.commands.Command;
import ding.commands.RedoCommand;
import ding.commands.UndoCommand;
import ding.commands.UndoableCommand;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;

/**
 * The main Ding class that initializes the application components and processes user input.
 */
public class Ding {
    private final TaskManager taskManager;
    private final Parser parser;
    private final List<String> startupWarnings;
    private final Deque<UndoableCommand> undoStack = new ArrayDeque<>();
    private final Deque<UndoableCommand> redoStack = new ArrayDeque<>();

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
    public String getResponse(String input) throws DingException {
        Command command = parser.parse(input);

        // Handle undo and redo commands
        if (command instanceof UndoCommand) {
            return handleUndo();
        }
        if (command instanceof RedoCommand) {
            return handleRedo();
        }

        String response = command.execute(taskManager);

        // Maintain undo/redo stacks for undoable commands
        if (command instanceof UndoableCommand) {
            undoStack.push((UndoableCommand) command);
            redoStack.clear();
        }
        return response;
    }

    private String handleUndo() throws DingException {
        if (undoStack.isEmpty()) {
            throw new DingException(Messages.ERROR_NOTHING_TO_UNDO);
        }
        UndoableCommand command = undoStack.pop();
        String response = command.undo(taskManager);
        redoStack.push(command);
        return response;
    }

    private String handleRedo() throws DingException {
        if (redoStack.isEmpty()) {
            throw new DingException(Messages.ERROR_NOTHING_TO_REDO);
        }
        UndoableCommand command = redoStack.pop();
        String response = command.execute(taskManager);
        undoStack.push(command);
        return response;
    }

}
