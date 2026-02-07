package ding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import ding.exceptions.DingException;
import ding.ui.Messages;
import ding.tasks.DeadlineTask;
import ding.tasks.EventTask;
import ding.tasks.Task;
import ding.tasks.TodoTask;

/**
 * Handles reading and writing tasks to disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the default data file path (data/ding.txt).
     */
    public Storage() {
        this(Paths.get("data", "ding.txt"));
    }

    /**
     * Constructs a Storage object with a custom file path.
     *
     * @param filePath the path to the storage file
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all tasks from the storage file.
     * Creates the data directory if it does not exist.
     * Skips corrupted lines and continues loading remaining tasks.
     *
     * @return an ArrayList of Task objects loaded from storage
     * @throws DingException if a fatal error occurs while reading the file
     */
    public ArrayList<Task> load() throws DingException {
        return load(null);
    }

    /**
     * Loads all tasks from the storage file and collects warnings for corrupted lines.
     * Creates the data directory if it does not exist.
     * Skips corrupted lines and continues loading remaining tasks.
     *
     * @param warnings optional list to collect warning messages
     * @return an ArrayList of Task objects loaded from storage
     * @throws DingException if a fatal error occurs while reading the file
     */
    public ArrayList<Task> load(List<String> warnings) throws DingException {
        createDataDirectories();
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                addTaskFromLine(line, lineNumber, tasks, warnings);
            }
        } catch (IOException e) {
            throw new DingException(String.format(Messages.ERROR_STORAGE_LOAD_FAILED, e.getMessage()));
        }
        return tasks;
    }

    /**
     * Saves all tasks to the storage file.
     * Creates the data directory if it does not exist.
     * Overwrites the existing file with the current task list.
     *
     * @param tasks the ArrayList of Task objects to save
     * @throws DingException if an error occurs while writing to the file
     */
    public void save(ArrayList<Task> tasks) throws DingException {
        createDataDirectories();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DingException(String.format(Messages.ERROR_STORAGE_SAVE_FAILED, e.getMessage()));
        }
    }

    private void createDataDirectories() throws DingException {
        Path parent = filePath.getParent();
        if (parent == null) {
            return;
        }
        try {
            Files.createDirectories(parent);
        } catch (IOException e) {
            throw new DingException(String.format(Messages.ERROR_STORAGE_CREATE_DIR_FAILED, e.getMessage()));
        }
    }

    private Task deserializeLine(String line) throws DingException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new DingException(Messages.ERROR_STORAGE_MISSING_FIELDS);
        }

        String type = parts[0].trim();
        boolean isDone = "1".equals(parts[1].trim());
        String description = parts[2].trim();

        return switch (type) {
            case "T" -> new TodoTask(description, isDone);
            case "D" -> deserializeDeadline(parts, isDone, description);
            case "E" -> deserializeEvent(parts, isDone, description);
            default -> throw new DingException(String.format(Messages.ERROR_STORAGE_UNKNOWN_TASK_TYPE, type));
        };
    }

    private Task deserializeDeadline(
            String[] parts, boolean isDone, String description) throws DingException {
        if (parts.length < 4) {
            throw new DingException(Messages.ERROR_STORAGE_DEADLINE_MISSING_BY);
        }
        String by = parts[3].trim();
        LocalDateTime parsedBy = parseStoredDateTime(by);
        return new DeadlineTask(description, parsedBy, isDone);
    }

    private Task deserializeEvent(
            String[] parts, boolean isDone, String description) throws DingException {
        if (parts.length < 5) {
            throw new DingException(Messages.ERROR_STORAGE_EVENT_MISSING_RANGE);
        }
        String from = parts[3].trim();
        String to = parts[4].trim();
        LocalDateTime parsedFrom = parseStoredDateTime(from);
        LocalDateTime parsedTo = parseStoredDateTime(to);
        return new EventTask(description, parsedFrom, parsedTo, isDone);
    }

    private LocalDateTime parseStoredDateTime(String value) throws DingException {
        try {
            return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            } catch (DateTimeParseException ex) {
                throw new DingException(String.format(Messages.ERROR_STORAGE_INVALID_DATE_TIME, value));
            }
        }
    }
    
    private void addTaskFromLine(
            String line,
            int lineNumber,
            ArrayList<Task> tasks,
            List<String> warnings) {
        try {
            tasks.add(deserializeLine(line));
        } catch (DingException e) {
            // Skip corrupted lines but continue loading the rest.
            String message = String.format(Messages.ERROR_STORAGE_SKIP_LINE, lineNumber, e.getMessage());
            if (warnings != null) {
                warnings.add(message);
            }
        }
    }
}
