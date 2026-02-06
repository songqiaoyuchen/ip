package ding.commands;

import ding.TaskManager;
import ding.ui.Messages;

public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks in the task manager.
     *
     * @param taskManager the TaskManager containing the tasks to list
     * @return a message listing all tasks
     */
    @Override
    public String execute(TaskManager taskManager) {
        String message = taskManager.toString().equals(Messages.EMPTY_LIST) 
            ? Messages.EMPTY_LIST
            : String.format(Messages.TASK_LIST_HEADER, taskManager);
        return message;
    }
}
