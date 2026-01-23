package ding.commands;

import ding.TaskManager;
import ding.tasks.TodoTask;
import ding.exceptions.DingException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskManager taskManager) throws DingException {
        TodoTask newTodo = new TodoTask(description);
        taskManager.addTask(newTodo);
        return "Ding: Got it. I've added this task to your to-do list. "
            + newTodo.toString() + "\n"
            + "Ding: You now have " + taskManager.getTaskCount() 
            + " tasks in the list. So hardworking!";
    }
    
}
