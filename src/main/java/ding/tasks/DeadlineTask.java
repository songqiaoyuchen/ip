package ding.tasks;
public class DeadlineTask extends Task {
    private String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getDescription(), by);
    }
}
