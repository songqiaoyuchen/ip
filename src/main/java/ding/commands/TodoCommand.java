package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.tasks.TodoTask;
import ding.ui.Messages;

/**
 * Command to add a todo task to the task manager.
 */
public class TodoCommand extends UndoableCommand {
    private String description;
    private int addedIndex = -1;

    /**
     * Constructs a TodoCommand with the task description.
     *
     * @param description the description of the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new TodoTask and adding it to the task manager.
     *
     * @param taskManager the TaskManager to add the task to
     * @return a confirmation message of the added task
     * @throws DingException if an error occurs while adding the task
     */
    @Override
    public String execute(TaskManager taskManager) throws DingException {
        TodoTask newTodo = new TodoTask(description);
        taskManager.addTask(newTodo);
        addedIndex = taskManager.getTaskCount() - 1;
        String message = String.format(Messages.TASK_ADDED, newTodo)
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
        return message;
    }

    @Override
    public String undo(TaskManager taskManager) throws DingException {
        if (addedIndex < 0) {
            throw new DingException(Messages.ERROR_TASK_NOT_FOUND);
        }
        Task task = taskManager.getTask(addedIndex);
        taskManager.deleteTask(addedIndex);
        return String.format(Messages.TASK_DELETED, task)
            + "\n" + String.format(Messages.TASK_COUNT_AFTER_DELETE, taskManager.getTaskCount());
    }
}
