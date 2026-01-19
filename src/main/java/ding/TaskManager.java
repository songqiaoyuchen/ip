package ding;
import java.util.ArrayList;

import ding.exceptions.DingException;
import ding.tasks.Task;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<Task>();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws DingException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new DingException("Uhhm, I can't find that task...");
        }
    }

    public Task markTaskDone(int index) throws DingException {
        Task task = this.getTask(index);
        if (task.isDone()) {
            throw new DingException("Ha! This task is already marked as done."
                + "Don't forget your hard work earlier :)"
            );
        }
        task.markDone();
        return task;
    }

    public Task markTaskUndone(int index) throws DingException {
        Task task = this.getTask(index);
        if (!task.isDone()) {
            throw new DingException("This task is already marked as not done."
                + "Let's get it done soon :)"
            );
        }
        task.markUndone();
        return task;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }
}
