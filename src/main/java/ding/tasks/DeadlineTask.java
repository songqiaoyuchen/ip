package ding.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A deadline task has a description and a due date/time.
 */
public class DeadlineTask extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy");

    private final LocalDateTime by;

    /**
     * Constructs a DeadlineTask with a description and due date/time.
     *
     * @param description the task description
     * @param by the deadline date and time
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a DeadlineTask with a description, due date/time, and completion status.
     *
     * @param description the task description
     * @param by the deadline date and time
     * @param isDone the completion status
     */
    public DeadlineTask(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     * Format: "[D][status] description (by: due date)"
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Serializes the deadline task to a storage format.
     * Format: "D | isDone | description | due_date_time (ISO format)"
     *
     * @return the serialized deadline task
     */
    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getDescription(), by);
    }
}
