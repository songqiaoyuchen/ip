package ding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import ding.exceptions.DingException;
import ding.tasks.DeadlineTask;
import ding.tasks.EventTask;
import ding.tasks.Task;
import ding.tasks.TodoTask;

/**
 * Handles reading and writing tasks to disk.
 */
public class Storage {
	private final Path filePath;

	public Storage() {
		this(Paths.get("data", "ding.txt"));
	}

	public Storage(Path filePath) {
		this.filePath = filePath;
	}

	public ArrayList<Task> load() throws DingException {
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
				try {
					tasks.add(deserializeLine(line));
				} catch (DingException e) {
					// Skip corrupted lines but continue loading the rest.
					System.err.println("Skipping corrupted entry at line " + lineNumber + ": " + e.getMessage());
				}
			}
		} catch (IOException e) {
			throw new DingException("Failed to load tasks from storage: " + e.getMessage());
		}
		return tasks;
	}

	public void save(ArrayList<Task> tasks) throws DingException {
		createDataDirectories();
		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
			for (Task task : tasks) {
				writer.write(task.serialize());
				writer.newLine();
			}
		} catch (IOException e) {
			throw new DingException("Failed to save tasks to storage: " + e.getMessage());
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
			throw new DingException("Unable to create data directory: " + e.getMessage());
		}
	}

	private Task deserializeLine(String line) throws DingException {
		String[] parts = line.split("\\s*\\|\\s*");
		if (parts.length < 3) {
			throw new DingException("Not enough fields in saved task");
		}

		String type = parts[0].trim();
		boolean isDone = "1".equals(parts[1].trim());
		String description = parts[2].trim();

		return switch (type) {
			case "T" -> new TodoTask(description, isDone);
			case "D" -> deserializeDeadline(parts, isDone, description);
			case "E" -> deserializeEvent(parts, isDone, description);
			default -> throw new DingException("Unknown task type: " + type);
		};
	}

	private Task deserializeDeadline(
        String[] parts, boolean isDone, String description) throws DingException {
		if (parts.length < 4) {
			throw new DingException("Deadline entry missing due date");
		}
		String by = parts[3].trim();
		return new DeadlineTask(description, by, isDone);
	}

	private Task deserializeEvent(
        String[] parts, boolean isDone, String description) throws DingException {
		if (parts.length < 5) {
			throw new DingException("Event entry missing from/to fields");
		}
		String from = parts[3].trim();
		String to = parts[4].trim();
		return new EventTask(description, from, to, isDone);
	}
}
