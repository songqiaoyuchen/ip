package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
import ding.ui.Ui;

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
