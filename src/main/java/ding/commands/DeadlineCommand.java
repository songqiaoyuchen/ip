package ding.commands;

import ding.tasks.DeadlineTask;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(ding.TaskManager taskManager) {
        DeadlineTask newDeadline = new DeadlineTask(description, by);
        taskManager.addTask(newDeadline);
        return "Ding: Got it. I've added this task to your to-do list.\n"
            + newDeadline.toString() + "\n"
            + "Ding: You now have " + taskManager.getTaskCount() 
            + " tasks in the list. So hardworking!";
    }
    
}
