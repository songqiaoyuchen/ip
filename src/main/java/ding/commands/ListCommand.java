package ding.commands;

import ding.TaskManager;

public class ListCommand extends Command {
    @Override
    public String execute(TaskManager taskManager) {
        return "Ding: Here are the tasks you have added:\n\n" + taskManager.toString();
    }
}
