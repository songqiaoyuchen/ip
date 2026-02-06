package ding.commands;

import ding.TaskManager;
import ding.ui.Messages;

public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param taskManager the TaskManager (unused)
     * @return a goodbye message
     */
    @Override
    public String execute(TaskManager taskManager) {
        return Messages.GOODBYE;
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