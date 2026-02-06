package ding.commands;

import ding.TaskManager;
import ding.tasks.EventTask;
import ding.ui.Messages;
import ding.exceptions.DingException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

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
        String message = String.format(Messages.TASK_ADDED, newEvent)
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
        return message;
    }
    
}
