package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
import ding.ui.Ui;
import java.util.ArrayList;

/**
 * Finds tasks whose descriptions contain the given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the keyword to search for.
     *
     * @param keyword the search keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword == null ? "" : keyword.trim();
    }

    /**
     * Executes the find command by searching tasks and displaying the matches.
     *
     * @param taskManager the TaskManager containing tasks
     * @param ui the Ui used to show messages
     * @throws DingException if the keyword is empty
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DingException {
        if (keyword.isEmpty()) {
            throw new DingException("Please provide a keyword to search for.");
        }

        ArrayList<Task> matches = taskManager.findTasks(keyword);
        if (matches.isEmpty()) {
            ui.showMessage(Messages.FIND_NO_MATCHES);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append('.').append(matches.get(i)).append("\n");
        }
        String message = String.format(Messages.FIND_RESULTS, sb.toString().stripTrailing());
        ui.showMessage(message);
    }
}
