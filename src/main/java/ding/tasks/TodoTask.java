package ding.tasks;
// A task without a specific date or time.

/**
 * Represents a simple todo task without a specific date or time.
 * A todo task has only a description and completion status.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask with a description.
     *
     * @param description the task description
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructs a TodoTask with a description and completion status.
     *
     * @param description the task description
     * @param isDone the completion status
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo task.
     * Format: "[T][status] description"
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Serializes the todo task to a storage format.
     * Format: "T | isDone | description"
     *
     * @return the serialized todo task
     */
    @Override
    public String serialize() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, getDescription());
    }
}
