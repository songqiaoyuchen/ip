package ding.commands;

import ding.TaskManager;
import ding.ui.Messages;
import ding.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param taskManager the TaskManager (unused)
     * @param ui the Ui object for displaying the goodbye message
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.showMessage(Messages.GOODBYE);
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return true, as this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}