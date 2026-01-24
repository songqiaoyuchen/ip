package ding.commands;

import ding.TaskManager;
import ding.tasks.TodoTask;
import ding.ui.Messages;
import ding.ui.Ui;
import ding.exceptions.DingException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        TodoTask newTodo = new TodoTask(description);
        taskManager.addTask(newTodo);
        String message = String.format(Messages.TASK_ADDED, newTodo.toString())
            + "\n" + String.format(Messages.TASK_COUNT, taskManager.getTaskCount());
        ui.showMessage(message);
    }
    
}
