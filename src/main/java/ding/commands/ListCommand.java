package ding.commands;

import ding.TaskManager;
import ding.ui.Messages;
import ding.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        String message = taskManager.toString().equals(Messages.EMPTY_LIST) 
            ? Messages.EMPTY_LIST
            : String.format(Messages.TASK_LIST_HEADER, taskManager.toString());
        ui.showMessage(message);
    }
}
