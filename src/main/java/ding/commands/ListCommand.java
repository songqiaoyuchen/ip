package ding.commands;

import ding.TaskManager;
import ding.ui.Messages;
import ding.ui.Ui;

public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks in the task manager.
     *
     * @param taskManager the TaskManager containing the tasks to list
     * @param ui the Ui object for displaying the task list
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        String message = taskManager.toString().equals(Messages.EMPTY_LIST) 
            ? Messages.EMPTY_LIST
            : String.format(Messages.TASK_LIST_HEADER, taskManager);
        ui.showMessage(message);
    }
}
