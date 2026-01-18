import java.util.ArrayList;

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

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Task markTaskDone(int index) {
        Task task = this.getTask(index);
        task.markDone();
        return task;
    }

    public Task markTaskUndone(int index) {
        Task task = this.getTask(index);
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
