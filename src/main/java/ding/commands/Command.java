package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.ui.Ui;

/**
 * Abstract base class for all commands.
 * Represents an action that can be executed by the application.
 */
public abstract class Command {
    /**
     * Executes the command with the given task manager and UI.
     *
     * @param taskManager the TaskManager to operate on
     * @param ui the Ui object for displaying messages
     * @throws DingException if an error occurs during execution
     */
    public abstract void execute(TaskManager taskManager, Ui ui) throws DingException;

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this is an exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
