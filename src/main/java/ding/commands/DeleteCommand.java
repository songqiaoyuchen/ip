package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskManager taskManager) throws DingException {
        Task task = taskManager.getTask(taskIndex);
        taskManager.deleteTask(taskIndex);
        return "Ding: Noted. I've removed this task:\n" + task.toString()
            + "\nDing: You now have " + taskManager.getTaskCount() 
            + " tasks in the list. Keep going!";
    }
}
