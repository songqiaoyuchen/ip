package ding.commands;

import ding.TaskManager;
import ding.tasks.DeadlineTask;
import ding.ui.Messages;
import ding.ui.Ui;
import ding.exceptions.DingException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        DeadlineTask newDeadline = new DeadlineTask(description, by);
        taskManager.addTask(newDeadline);
        String message = String.format(Messages.TASK_ADDED, newDeadline.toString())
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
        ui.showMessage(message);
    }
    
}
