package ding.commands;

import ding.TaskManager;
import ding.tasks.EventTask;
import ding.exceptions.DingException;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskManager taskManager) throws DingException {
        EventTask newEvent = new EventTask(description, from, to);
        taskManager.addTask(newEvent);
        return "Ding: Got it. I've added this task to your to-do list.\n"
            + newEvent.toString() + "\n"
            + "Ding: You now have " + taskManager.getTaskCount() 
            + " tasks in the list. So hardworking!";
    }
    
}
