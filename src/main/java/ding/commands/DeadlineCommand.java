package ding.commands;

import java.time.LocalDateTime;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.DeadlineTask;
import ding.tasks.Task;
import ding.ui.Messages;

/**
 * Command to add a deadline task to the task manager.
 */
public class DeadlineCommand extends UndoableCommand {
    private String description;
    private LocalDateTime by;
    private int addedIndex = -1;

    /**
     * Constructs a DeadlineCommand with a description and due date/time.
     *
     * @param description the task description
     * @param by the deadline date and time
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command by creating a new DeadlineTask and adding it to the task manager.
     *
     * @param taskManager the TaskManager to add the task to
     * @return a confirmation message of the added task
     * @throws DingException if an error occurs while adding the task
     */
    @Override
    public String execute(TaskManager taskManager) throws DingException {
        DeadlineTask newDeadline = new DeadlineTask(description, by);
        taskManager.addTask(newDeadline);
        addedIndex = taskManager.getTaskCount() - 1;
        String message = String.format(Messages.TASK_ADDED, newDeadline)
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
