package ding.tasks;

/**
 * Abstract base class representing a task.
 * A task has a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with a description and marks it as incomplete.
     *
     * @param description the task description
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructs a Task with a description and completion status.
     *
     * @param description the task description
     * @param isDone the initial completion status
     */
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
        return this.isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status symbol of the task.
     * "[X]" for completed tasks, "[ ]" for incomplete tasks.
     *
     * @return the status symbol
     */
    protected String getStatus() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Serializes the task to a string format for storage.
     *
     * @return the serialized task string
     */
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
