package ding.commands;

import ding.Messages;
import ding.TaskManager;
import ding.Ui;
import ding.exceptions.DingException;
import ding.tasks.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        Task task = taskManager.markTaskUndone(taskIndex);
        ui.showMessage(String.format(Messages.TASK_MARKED_UNDONE, task.toString()));
    }
    
}
