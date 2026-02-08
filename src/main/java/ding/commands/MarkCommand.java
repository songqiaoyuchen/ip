package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;

/**
 * Command to mark a task as done in the task manager.
 */
public class MarkCommand extends UndoableCommand {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the index of the task to mark as done.
     *
     * @param taskIndex the zero-based index of the task to mark
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param taskManager the TaskManager containing the task to mark
     * @return a confirmation message of the marked task
     * @throws DingException if the task is not found or is already marked as done
     */
    @Override
    public String execute(TaskManager taskManager) throws DingException {
        Task task = taskManager.markTaskDone(taskIndex);
        return String.format(Messages.TASK_MARKED_DONE, task.toString());
    }

    @Override
    public String undo(TaskManager taskManager) throws DingException {
        Task task = taskManager.markTaskUndone(taskIndex);
        return String.format(Messages.TASK_MARKED_UNDONE, task.toString());
    }
}
