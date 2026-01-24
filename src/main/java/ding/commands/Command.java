package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws DingException;

    public boolean isExit() {
        return false;
    }
}
