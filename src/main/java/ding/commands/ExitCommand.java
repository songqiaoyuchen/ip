package ding.commands;

import ding.Messages;
import ding.TaskManager;
import ding.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.showMessage(Messages.GOODBYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}