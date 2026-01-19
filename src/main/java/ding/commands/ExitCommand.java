package ding.commands;

import ding.TaskManager;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskManager taskManager) {
        return "Ding: Goodbye! We will meet again soon.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}