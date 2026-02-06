package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.TodoTask;
import ding.ui.Messages;

/**
 * Command to add a todo task to the task manager.
 */
public class TodoCommand extends Command {
    private String description;

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
        String message = String.format(Messages.TASK_ADDED, newTodo)
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
        return message;
    }
}
