package ding.commands;

import ding.tasks.DeadlineTask;
import ding.exceptions.DingException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(ding.TaskManager taskManager) throws DingException {
        DeadlineTask newDeadline = new DeadlineTask(description, by);
        taskManager.addTask(newDeadline);
        return "Ding: Got it. I've added this task to your to-do list.\n"
            + newDeadline.toString() + "\n"
            + "Ding: You now have " + taskManager.getTaskCount() 
            + " tasks in the list. So hardworking!";
    }
    
}
