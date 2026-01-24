package ding.commands;

import ding.Messages;
import ding.TaskManager;
import ding.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.showMessage(String.format(Messages.TASK_LIST_HEADER, taskManager.toString()));
    }
}
