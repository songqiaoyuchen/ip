package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the index of the task to delete.
     *
     * @param taskIndex the zero-based index of the task to delete
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the specified task from the task manager.
     *
     * @param taskManager the TaskManager to delete the task from
     * @return a confirmation message of the deleted task
     * @throws DingException if the task is not found
     */
    @Override
    public String execute(TaskManager taskManager) throws DingException {
        Task task = taskManager.getTask(taskIndex);
        taskManager.deleteTask(taskIndex);
        String message = String.format(Messages.TASK_DELETED, task.toString())
            + "\n" + String.format(Messages.TASK_COUNT_AFTER_DELETE, taskManager.getTaskCount());
        return message;
    }
}
