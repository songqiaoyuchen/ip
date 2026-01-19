package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskManager taskManager) throws DingException {
        Task task = taskManager.getTask(taskIndex);
        if (task.isDone()) {
            throw new DingException("Ding: This task is already marked as done:\n" 
                + "Did you mean to unmark it instead?\n" + task.toString());
        }
        task.markDone();
        return "Ding: Nice! I've marked this task as done:\n" + task.toString();
    }   
    
}
