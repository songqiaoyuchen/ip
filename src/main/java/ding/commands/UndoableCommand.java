package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;

/**
 * Abstract base class for commands that can be undone and redone.
 */
public abstract class UndoableCommand extends Command {
    /**
     * Reverts the effects of a previously executed command.
     *
     * @param taskManager the TaskManager to operate on
     * @return a message resulting from the undo operation
     * @throws DingException if an error occurs during undo
     */
    public abstract String undo(TaskManager taskManager) throws DingException;
}
