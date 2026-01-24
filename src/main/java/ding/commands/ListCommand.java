package ding.commands;

import ding.TaskManager;
import ding.ui.Messages;
import ding.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.showMessage(String.format(Messages.TASK_LIST_HEADER, taskManager.toString()));
    }
}
