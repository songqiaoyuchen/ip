package ding;

import java.util.ArrayList;

import ding.exceptions.DingException;
import ding.tasks.Task;
import ding.ui.Messages;

public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskManager with a storage backend and an empty task list.
     *
     * @param storage the Storage object for persisting tasks
     */
    public TaskManager(Storage storage) {
        this(storage, new ArrayList<>());
    }

    /**
     * Constructs a TaskManager with a storage backend and initial tasks.
     *
     * @param storage the Storage object for persisting tasks
     * @param initialTasks the initial list of tasks to manage
     */
    public TaskManager(Storage storage, ArrayList<Task> initialTasks) {
        this.storage = storage;
        this.tasks = new ArrayList<>(initialTasks);
    }

    /**
     * Returns the number of tasks currently managed.
     *
     * @return the total count of tasks
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Adds a new task to the task list and persists it to storage.
     *
     * @param task the Task object to add
     * @throws DingException if an error occurs while saving to storage
     */
    public void addTask(Task task) throws DingException {
        tasks.add(task);
        persist();
    }

    /**
     * Retrieves a task by its zero-based index.
     *
     * @param index the zero-based index of the task
     * @return the Task object at the specified index
     * @throws DingException if the index is out of bounds
     */
    public Task getTask(int index) throws DingException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new DingException(Messages.ERROR_TASK_NOT_FOUND);
        }
    }

    /**
     * Marks a task as completed and persists the change to storage.
     *
     * @param index the zero-based index of the task to mark as done
     * @return the marked Task object
     * @throws DingException if the task is not found or is already marked as done
     */
    public Task markTaskDone(int index) throws DingException {
        Task task = this.getTask(index);
        if (task.isDone()) {
            throw new DingException(Messages.ERROR_TASK_ALREADY_DONE);
        }
        task.markDone();
        persist();
        return task;
    }

    /**
     * Marks a task as incomplete and persists the change to storage.
     *
     * @param index the zero-based index of the task to mark as undone
     * @return the unmarked Task object
     * @throws DingException if the task is not found or is already marked as undone
     */
    public Task markTaskUndone(int index) throws DingException {
        Task task = this.getTask(index);
        if (!task.isDone()) {
            throw new DingException(Messages.ERROR_TASK_ALREADY_UNDONE);
        }
        task.markUndone();
        persist();
        return task;
    }

    /**
     * Deletes a task from the task list and persists the change to storage.
     *
     * @param index the zero-based index of the task to delete
     * @throws DingException if the task is not found
     */
    public void deleteTask(int index) throws DingException {
        Task task = this.getTask(index);
        tasks.remove(task);
        persist();
    }

    /**
     * Finds tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword the search keyword
     * @return a list of matching tasks in their current order
     */
    public ArrayList<Task> findTasks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerKeyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Returns a formatted string representation of all tasks.
     * Each task is numbered starting from 1.
     *
     * @return a string containing the formatted task list, or an empty list message
     */
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
