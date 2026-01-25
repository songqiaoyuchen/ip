package ding;

import ding.commands.DeadlineCommand;
import ding.commands.DeleteCommand;
import ding.commands.EventCommand;
import ding.commands.ExitCommand;
import ding.commands.ListCommand;
import ding.commands.MarkCommand;
import ding.commands.TodoCommand;
import ding.commands.UnmarkCommand;
import ding.exceptions.DingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses a user input string and returns the corresponding Command object.
     *
     * @param input the user input string to parse
     * @return the Command object corresponding to the input
     * @throws DingException if the input is invalid or unrecognized
     */
    public Command parse(String input) throws DingException {
        if (input == null) {
            throw new DingException("Oops! I'm waiting for your command. Don't leave me hanging!");
        }
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new DingException("Oops! I'm waiting for your command. Don't leave me hanging!");
        }

        String[] parts = trimmed.split("\\s+", 2);
        String keyword = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        return switch (keyword) {
            case "bye"      -> new ExitCommand();
            case "list"     -> new ListCommand();
            case "mark"     -> new MarkCommand(parseIndex(args));
            case "unmark"   -> new UnmarkCommand(parseIndex(args));
            case "delete"   -> new DeleteCommand(parseIndex(args));
            case "todo"     -> parseTodo(args);
            case "deadline" -> parseDeadline(args);
            case "event"    -> parseEvent(args);
            default         -> throw new DingException("I'm sorry, I don't understand that yet.");
        };
    }

    /**
     * Parses a task index from a string argument.
     *
     * @param args the argument string containing the one-based task index
     * @return the zero-based task index
     * @throws DingException if the index is missing or invalid
     */
    private int parseIndex(String args) throws DingException {
        if (args.isBlank()) {
            throw new DingException("Which task? Please tell me the task number!");
        }
        try {
            int oneBased = Integer.parseInt(args.trim());
            return oneBased - 1;
        } catch (NumberFormatException e) {
            throw new DingException(
                "Hmm, that doesn't look like a valid task number. Could you try again?");
        }
    }

    /**
     * Parses a todo command and returns a TodoCommand object.
     *
     * @param args the arguments string containing the todo description
     * @return a TodoCommand object
     * @throws DingException if the description is missing
     */
    private TodoCommand parseTodo(String args) throws DingException {
        if (args.isBlank()) {
            throw new DingException(
                "A todo without a description? That won't do! Tell me what you need to do.");
        }
        return new TodoCommand(args.trim());
    }

    /**
     * Parses a deadline command and returns a DeadlineCommand object.
     * Expected format: "<description> /by <date/time>"
     *
     * @param args the arguments string containing description and deadline
     * @return a DeadlineCommand object
     * @throws DingException if the format is invalid or required fields are missing
     */
    private DeadlineCommand parseDeadline(String args) throws DingException {
        // format: <desc> /by <when>
        String rest = args.trim();
        int byPos = rest.toLowerCase().indexOf("/by ");
        if (byPos == -1) {
            throw new DingException(
                "I'd love to add that deadline! Just use: deadline <description> /by <when>");
        }

        String desc = rest.substring(0, byPos).trim();
        // "/by " has length 4; start after that
        String by = rest.substring(byPos + 4).trim();

        if (desc.isBlank()) throw new DingException("Your deadline needs a description! What's the task?");
        if (by.isBlank()) throw new DingException("Don't forget when it's due! Add a /by date please.");

        LocalDateTime byDateTime = parseDateTime(by);
        return new DeadlineCommand(desc, byDateTime);
    }

    /**
     * Parses a date/time string in various supported formats.
     * Supports formats like "yyyy-MM-dd HHmm", "d/M/uuuu HHmm", and date-only formats.
     *
     * @param input the date/time string to parse
     * @return a LocalDateTime object
     * @throws DingException if the input format is not recognized
     */
    private LocalDateTime parseDateTime(String input) throws DingException {
        String trimmed = input.trim();

        DateTimeFormatter[] dateTimeFormats = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm"),
            DateTimeFormatter.ofPattern("d-M-uuuu HHmm")
        };

        DateTimeFormatter[] dateOnlyFormats = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/uuuu"),
            DateTimeFormatter.ofPattern("d-M-uuuu")
        };

        for (DateTimeFormatter formatter : dateTimeFormats) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException ignored) {
                // try next format
            }
        }

        for (DateTimeFormatter formatter : dateOnlyFormats) {
            try {
                LocalDate date = LocalDate.parse(trimmed, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException ignored) {
                // try next format
            }
        }

        throw new DingException(
            "I couldn't understand that date/time. Try formats like 2019-12-02 1800 or 2/12/2019.");
    }

    /**
     * Parses an event command and returns an EventCommand object.
     * Expected format: "<description> /from <date/time> /to <date/time>"
     *
     * @param args the arguments string containing description and time range
     * @return an EventCommand object
     * @throws DingException if the format is invalid or required fields are missing
     */
    private EventCommand parseEvent(String args) throws DingException {
        // format: <desc> /from <start> /to <end>
        String rest = args.trim();
        int fromPos = rest.toLowerCase().indexOf("/from ");
        int toPos = rest.toLowerCase().indexOf("/to ");
        if (fromPos == -1 || toPos == -1 || toPos < fromPos) {
            throw new DingException(
                "Let me add that event for you! Just use: event <description> /from <start> /to <end>");
        }

        String desc = rest.substring(0, fromPos).trim();
        String from = rest.substring(fromPos + 6, toPos).trim();
        String to = rest.substring(toPos + 4).trim();

        if (desc.isBlank()) throw new DingException("Your event needs a description! What's happening?");
        if (from.isBlank() || to.isBlank()) {
            throw new DingException(
                "I need to know when your event is! Add /from and /to times please.");
        }

        LocalDateTime fromDateTime = parseDateTime(from);
        LocalDateTime toDateTime = parseDateTime(to);
        return new EventCommand(desc, fromDateTime, toDateTime);
    }
}
