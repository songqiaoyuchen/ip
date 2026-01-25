package ding;
import java.util.ArrayList;

import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;

public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskManager(Storage storage) {
        this(storage, new ArrayList<>());
    }

    public TaskManager(Storage storage, ArrayList<Task> initialTasks) {
        this.storage = storage;
        this.tasks = new ArrayList<>(initialTasks);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void addTask(Task task) throws DingException {
        tasks.add(task);
        persist();
    }

    public Task getTask(int index) throws DingException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new DingException(Messages.ERROR_TASK_NOT_FOUND);
        }
    }

    public Task markTaskDone(int index) throws DingException {
        Task task = this.getTask(index);
        if (task.isDone()) {
            throw new DingException(Messages.ERROR_TASK_ALREADY_DONE);
        }
        task.markDone();
        persist();
        return task;
    }

    public Task markTaskUndone(int index) throws DingException {
        Task task = this.getTask(index);
        if (!task.isDone()) {
            throw new DingException(Messages.ERROR_TASK_ALREADY_UNDONE);
        }
        task.markUndone();
        persist();
        return task;
    }

    public void deleteTask(int index) throws DingException {
        Task task = this.getTask(index);
        tasks.remove(task);
        persist();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return Messages.EMPTY_LIST;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    private void persist() throws DingException {
        storage.save(tasks);
    }
}
