package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
import ding.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the index of the task to unmark.
     *
     * @param taskIndex the zero-based index of the task to unmark
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by marking the specified task as incomplete.
     *
     * @param taskManager the TaskManager containing the task to unmark
     * @param ui the Ui object for displaying the confirmation
     * @throws DingException if the task is not found or is already unmarked
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        Task task = taskManager.markTaskUndone(taskIndex);
        ui.showMessage(String.format(Messages.TASK_MARKED_UNDONE, task.toString()));
    }
    
}
