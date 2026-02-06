package ding.commands;

import ding.TaskManager;
import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;
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
     * @return a message listing the found tasks
     * @throws DingException if the keyword is empty
     */
    @Override
    public String execute(TaskManager taskManager) throws DingException {
        if (keyword.isEmpty()) {
            throw new DingException("Please provide a keyword to search for.");
        }

        ArrayList<Task> matches = taskManager.findTasks(keyword);
        if (matches.isEmpty()) {
            return Messages.FIND_NO_MATCHES;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append('.').append(matches.get(i)).append("\n");
        }
        String message = String.format(Messages.FIND_RESULTS, sb.toString().stripTrailing());
        return message;
    }
}
