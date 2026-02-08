package ding.commands;

import java.time.LocalDateTime;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.EventTask;
import ding.tasks.Task;
import ding.ui.Messages;

/**
 * Command to add an event task to the task manager.
 */
public class EventCommand extends UndoableCommand {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;
    private int addedIndex = -1;

    /**
     * Constructs an EventCommand with a description and time range.
     *
     * @param description the event description
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating a new EventTask and adding it to the task manager.
     *
     * @param taskManager the TaskManager to add the task to
     * @return a confirmation message of the added task
     * @throws DingException if an error occurs while adding the task
     */
    @Override
    public String execute(TaskManager taskManager) throws DingException {
        EventTask newEvent = new EventTask(description, from, to);
        taskManager.addTask(newEvent);
        addedIndex = taskManager.getTaskCount() - 1;
        String message = String.format(Messages.TASK_ADDED, newEvent)
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
