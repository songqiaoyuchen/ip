package ding.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs during a specific time period.
 * An event task has a description and a start and end date/time.
 */
public class EventTask extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an EventTask with a description and time range.
     *
     * @param description the task description
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an EventTask with a description, time range, and completion status.
     *
     * @param description the task description
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     * @param isDone the completion status
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     * Format: "[E][status] description (from: start to: end)"
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMAT)
                + " to: " + to.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Serializes the event task to a storage format.
     * Format: "E | isDone | description | start_date_time | end_date_time"
     *
     * @return the serialized event task
     */
    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s | %s", 
                isDone() ? 1 : 0, getDescription(), from, to);
    }
}
