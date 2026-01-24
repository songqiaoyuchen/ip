package ding.commands;

import ding.Messages;
import ding.TaskManager;
import ding.Ui;
import ding.exceptions.DingException;
import ding.tasks.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        Task task = taskManager.markTaskDone(taskIndex);
        ui.showMessage(String.format(Messages.TASK_MARKED_DONE, task.toString()));
    }   
    
}
