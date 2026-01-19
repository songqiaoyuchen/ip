package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;

public abstract class Command {
    public abstract String execute(TaskManager taskManager) throws DingException;

    public boolean isExit() {
        return false;
    }
}
