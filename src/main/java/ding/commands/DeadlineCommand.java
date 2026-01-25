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
     * @param ui the Ui object for displaying the success message
     * @throws DingException if an error occurs while adding the task
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        DeadlineTask newDeadline = new DeadlineTask(description, by);
        taskManager.addTask(newDeadline);
        String message = String.format(Messages.TASK_ADDED, newDeadline)
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
        ui.showMessage(message);
    }
    
}
