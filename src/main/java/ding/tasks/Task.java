package ding.tasks;
public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether the task is marked as completed.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    protected String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public abstract String serialize();

    /**
     * Returns a string representation of the task.
     *
     * @return the status marker and description
     */
    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }
}
