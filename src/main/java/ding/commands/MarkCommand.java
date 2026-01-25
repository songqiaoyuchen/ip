package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
import ding.ui.Ui;

public class MarkCommand extends Command {
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
     * @param ui the Ui object for displaying the confirmation
     * @throws DingException if the task is not found or is already marked as done
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        Task task = taskManager.markTaskDone(taskIndex);
        ui.showMessage(String.format(Messages.TASK_MARKED_DONE, task.toString()));
    }   
    
}
