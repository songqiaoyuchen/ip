package ding.tasks;
// A task without a specific date or time.

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, getDescription());
    }
}
