package ding;
import java.util.ArrayList;

import ding.commands.Command;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
import ding.ui.Ui;

public class Ding {
    
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        Storage storage = new Storage();
        ArrayList<Task> initialTasks = new ArrayList<Task>();
        try {
            initialTasks = storage.load();
        } catch (DingException e) {
            ui.showError(String.format(Messages.ERROR_LOAD_TASKS, e.getMessage()));
        }

        TaskManager taskManager = new TaskManager(storage, initialTasks);
        Parser parser = new Parser();
        while (true) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parse(userInput);
                command.execute(taskManager, ui);
                if (command.isExit()) {
                    break;
                }
            } catch (DingException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLineBreak();
            }
        }
        ui.close();
    }
}
