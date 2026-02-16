# Ding User Guide

Ding is a task management chatbot from the Highlands, designed to help you track your to-do items, deadlines, and events through a friendly conversational interface. With just a few keystrokes, you can add tasks, mark them complete, and stay organized.

## Quick Start

1. Ensure you have Java 17 or above installed on your computer.

2. Download the latest `.jar` file from the [releases page](https://github.com/your-username/ip/releases).

3. Copy the file to the folder you want to use as your home folder for Ding.

4. Open a command terminal, navigate to the folder containing the jar file, and run:
   ```
   java -jar ip.jar
   ```

5. A window should appear with Ding greeting you. Type your commands in the text box at the bottom and press Enter or click Send.

6. Try these example commands to get started:
   - `list` — View all your tasks
   - `todo Buy groceries` — Add a simple to-do
   - `deadline Submit report /by 2026-02-20 1800` — Add a deadline
   - `event Team meeting /from 2026-02-18 1400 /to 2026-02-18 1500` — Add an event

## Features

### Adding a To-Do Task: `todo`

Adds a simple task with no deadline to your task list.

**Format:** `todo DESCRIPTION`

**Examples:**
- `todo Buy groceries`
- `todo Call mom`

---

### Adding a Deadline Task: `deadline`

Adds a task with a deadline date and time.

**Format:** `deadline DESCRIPTION /by DATE TIME`

**Supported date/time formats:**
- `yyyy-MM-dd HHmm` (e.g., `2026-02-20 1800`)
- `d/M/uuuu HHmm` (e.g., `20/2/2026 1800`)
- `yyyy-MM-dd` (e.g., `2026-02-20`) — defaults to 00:00

**Examples:**
- `deadline Submit report /by 2026-02-20 1800`
- `deadline Finish project /by 20/2/2026 2359`

---

### Adding an Event Task: `event`

Adds a task that occurs during a specific time range.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

**Examples:**
- `event Team meeting /from 2026-02-18 1400 /to 2026-02-18 1500`
- `event Conference /from 2026-03-01 0900 /to 2026-03-03 1700`

---

### Listing All Tasks: `list`

Shows all tasks in your task list.

**Format:** `list`

---

### Marking a Task as Done: `mark`

Marks a task as completed.

**Format:** `mark INDEX`

- The index is the task number shown in your task list (starting from 1).

**Examples:**
- `mark 1` — Mark the 1st task as complete
- `mark 3` — Mark the 3rd task as complete

---

### Marking a Task as Undone: `unmark`

Marks a completed task as pending again.

**Format:** `unmark INDEX`

**Examples:**
- `unmark 1` — Return the 1st task to pending
- `unmark 2` — Return the 2nd task to pending

---

### Deleting a Task: `delete`

Removes a task from your list.

**Format:** `delete INDEX`

**Examples:**
- `delete 1` — Delete the 1st task
- `delete 3` — Delete the 3rd task

---

### Finding Tasks: `find`

Searches for tasks whose descriptions contain the given keywords.

**Format:** `find KEYWORD [MORE_KEYWORDS]`

- Search is case-insensitive
- Only full words are matched (e.g., `buy` won't match `buying`)
- Tasks matching any keyword are returned

**Examples:**
- `find groceries` — Finds all tasks with "groceries"
- `find report project` — Finds tasks with "report" or "project"

---

### Undoing an Action: `undo`

Reverts the most recent action (add, mark, unmark, or delete a task).

**Format:** `undo`

---

### Redoing an Action: `redo`

Repeats the most recently undone action.

**Format:** `redo`

---

### Exiting the Program: `bye`

Closes Ding and saves your tasks.

**Format:** `bye`

---

## Saving Your Data

All your tasks are automatically saved to disk after each command. There's no need to save manually. Your tasks persist between sessions.

---

## FAQ

**Q: Where are my tasks saved?**
A: Your tasks are saved in a text file at `data/ding.txt` in the same directory where you run Ding.

**Q: Can I edit the saved data file directly?**
A: You can, but be careful! The file is in a specific format. Incorrect edits may cause data loss.

**Q: How do I transfer my tasks to another computer?**
A: Copy the `data/ding.txt` file to the other computer in the same location relative to the jar file.

**Q: What if I make a mistake?**
A: Use the `undo` command to revert your most recent action, then `redo` to restore it if needed.

---

## Command Summary

| Action | Format | Example |
|--------|--------|---------|
| Add To-Do | `todo DESCRIPTION` | `todo Buy groceries` |
| Add Deadline | `deadline DESCRIPTION /by DATE TIME` | `deadline Report /by 2026-02-20 1800` |
| Add Event | `event DESCRIPTION /from START /to END` | `event Meeting /from 2026-02-18 1400 /to 1500` |
| List | `list` | `list` |
| Mark Done | `mark INDEX` | `mark 1` |
| Mark Undone | `unmark INDEX` | `unmark 1` |
| Delete | `delete INDEX` | `delete 2` |
| Find | `find KEYWORD [...]` | `find groceries` |
| Undo | `undo` | `undo` |
| Redo | `redo` | `redo` |
| Exit | `bye` | `bye` |