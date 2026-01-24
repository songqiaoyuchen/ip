package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
import ding.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        Task task = taskManager.getTask(taskIndex);
        taskManager.deleteTask(taskIndex);
        String message = String.format(Messages.TASK_DELETED, task.toString())
            + "\n" + String.format(Messages.TASK_COUNT_AFTER_DELETE, taskManager.getTaskCount());
        ui.showMessage(message);
    }
}
