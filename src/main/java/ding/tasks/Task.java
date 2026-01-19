package ding.tasks;
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    private String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }
}
