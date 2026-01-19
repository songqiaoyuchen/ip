package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskManager taskManager) throws DingException {
        Task task = taskManager.getTask(taskIndex);
        if (!task.isDone()) {
            throw new DingException("Ding: This task is already marked as not done:\n" 
                + "Did you mean to mark it as done instead?\n" + task.toString());
        }
        task.markUndone();
        return "Ding: OK, I've marked this task as not done yet:\n" + task.toString();
    }
    
}
