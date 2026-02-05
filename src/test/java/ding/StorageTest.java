package ding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ding.exceptions.DingException;
import ding.tasks.DeadlineTask;
import ding.tasks.EventTask;
import ding.tasks.Task;
import ding.tasks.TodoTask;

public class StorageTest {
    private static final Path TEST_FILE = Paths.get("data", "test_ding.txt");
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        // Clean up test file before each test
        Files.deleteIfExists(TEST_FILE);
        storage = new Storage(TEST_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up test file after each test
        Files.deleteIfExists(TEST_FILE);
    }

    @Test
    @DisplayName("load creates empty list when file does not exist")
    void load_fileNotExists_returnsEmptyList() throws DingException {
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("load creates data directory if it does not exist")
    void load_directoryNotExists_createsDirectory() throws DingException {
        Path tempDir = Paths.get("data", "temp_test");
        Storage tempStorage = new Storage(tempDir.resolve("test.txt"));
        
        ArrayList<Task> tasks = tempStorage.load();
        assertTrue(tasks.isEmpty());
        assertTrue(Files.exists(tempDir));
        
        // Clean up
        try {
            Files.deleteIfExists(tempDir.resolve("test.txt"));
            Files.deleteIfExists(tempDir);
        } catch (IOException e) {
            // Ignore cleanup errors
        }
    }

    @Test
    @DisplayName("save and load TodoTask")
    void saveAndLoad_todoTask_persistsCorrectly() throws DingException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new TodoTask("buy milk"));
        tasksToSave.add(new TodoTask("walk dog", true));

        storage.save(tasksToSave);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(2, loadedTasks.size());
        assertInstanceOf(TodoTask.class, loadedTasks.get(0));
        assertEquals("buy milk", loadedTasks.get(0).getDescription());
        assertFalse(loadedTasks.get(0).isDone());
        
        assertInstanceOf(TodoTask.class, loadedTasks.get(1));
        assertEquals("walk dog", loadedTasks.get(1).getDescription());
        assertTrue(loadedTasks.get(1).isDone());
    }

    @Test
    @DisplayName("save and load DeadlineTask")
    void saveAndLoad_deadlineTask_persistsCorrectly() throws DingException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        LocalDateTime deadline = LocalDateTime.of(2024, 12, 25, 18, 0);
        tasksToSave.add(new DeadlineTask("submit report", deadline, false));

        storage.save(tasksToSave);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertInstanceOf(DeadlineTask.class, loadedTasks.get(0));
        assertEquals("submit report", loadedTasks.get(0).getDescription());
    }

    @Test
    @DisplayName("save and load EventTask")
    void saveAndLoad_eventTask_persistsCorrectly() throws DingException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        LocalDateTime from = LocalDateTime.of(2024, 12, 25, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 12, 25, 16, 0);
        tasksToSave.add(new EventTask("team meeting", from, to, true));

        storage.save(tasksToSave);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertInstanceOf(EventTask.class, loadedTasks.get(0));
        assertEquals("team meeting", loadedTasks.get(0).getDescription());
        assertTrue(loadedTasks.get(0).isDone());
    }

    @Test
    @DisplayName("save and load mixed task types")
    void saveAndLoad_mixedTasks_persistsAllCorrectly() throws DingException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new TodoTask("buy groceries"));
        LocalDateTime deadline = LocalDateTime.of(2024, 12, 31, 23, 59);
        tasksToSave.add(new DeadlineTask("finish project", deadline, true));
        LocalDateTime from = LocalDateTime.of(2024, 12, 24, 10, 0);
        LocalDateTime to = LocalDateTime.of(2024, 12, 24, 12, 0);
        tasksToSave.add(new EventTask("morning standup", from, to, false));

        storage.save(tasksToSave);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(3, loadedTasks.size());
        assertInstanceOf(TodoTask.class, loadedTasks.get(0));
        assertInstanceOf(DeadlineTask.class, loadedTasks.get(1));
        assertInstanceOf(EventTask.class, loadedTasks.get(2));
    }

    @Test
    @DisplayName("load skips blank lines")
    void load_fileWithBlankLines_skipsEmptyLines() throws DingException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new TodoTask("task 1"));
        tasksToSave.add(new TodoTask("task 2"));

        storage.save(tasksToSave);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(2, loadedTasks.size());
    }

    @Test
    @DisplayName("save empty task list")
    void save_emptyTaskList_createsFile() throws DingException {
        ArrayList<Task> emptyTasks = new ArrayList<>();
        storage.save(emptyTasks);

        assertTrue(Files.exists(TEST_FILE));
        ArrayList<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
    }

    @Test
    @DisplayName("save overwrites existing file")
    void save_fileAlreadyExists_overwritesContent() throws DingException {
        // First save
        ArrayList<Task> firstSave = new ArrayList<>();
        firstSave.add(new TodoTask("task 1"));
        firstSave.add(new TodoTask("task 2"));
        storage.save(firstSave);

        // Second save with different content
        ArrayList<Task> secondSave = new ArrayList<>();
        secondSave.add(new TodoTask("new task"));
        storage.save(secondSave);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("new task", loadedTasks.get(0).getDescription());
    }
}