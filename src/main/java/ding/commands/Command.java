package ding.commands;

import ding.TaskManager;
import ding.Ui;
import ding.exceptions.DingException;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws DingException;

    public boolean isExit() {
        return false;
    }
}
