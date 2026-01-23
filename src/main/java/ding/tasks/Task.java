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

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    protected String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public abstract String serialize();

    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }
}
