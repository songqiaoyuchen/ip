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
        Task task = taskManager.markTaskDone(taskIndex);
        return "Ding: Nice! I've marked this task as done:\n" + task.toString();
    }   
    
}
