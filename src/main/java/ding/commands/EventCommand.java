package ding.commands;

import ding.TaskManager;
import ding.tasks.EventTask;
import ding.ui.Messages;
import ding.ui.Ui;
import ding.exceptions.DingException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        EventTask newEvent = new EventTask(description, from, to);
        taskManager.addTask(newEvent);
        String message = String.format(Messages.TASK_ADDED, newEvent.toString())
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
        ui.showMessage(message);
    }
    
}
