package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;

/**
 * Command to delete a task from the task manager.
 */
public class DeleteCommand extends UndoableCommand {
    private final int taskIndex;
    private Task deletedTask;

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
        deletedTask = task;
        taskManager.deleteTask(taskIndex);
        String message = String.format(Messages.TASK_DELETED, task.toString())
            + "\n" + String.format(Messages.TASK_COUNT_AFTER_DELETE, taskManager.getTaskCount());
        return message;
    }

    @Override
    public String undo(TaskManager taskManager) throws DingException {
        if (deletedTask == null) {
            throw new DingException(Messages.ERROR_TASK_NOT_FOUND);
        }
        taskManager.insertTask(taskIndex, deletedTask);
        return String.format(Messages.TASK_ADDED, deletedTask)
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
    }
}
