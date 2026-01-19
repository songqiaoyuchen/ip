package ding;

import ding.commands.*;
import ding.exceptions.DingException;

public class Parser {

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
            case "todo"     -> parseTodo(args);
            case "deadline" -> parseDeadline(args);
            case "event"    -> parseEvent(args);
            default         -> throw new DingException("I'm sorry, I don't understand that yet.");
        };
    }

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

    private TodoCommand parseTodo(String args) throws DingException {
        if (args.isBlank()) {
            throw new DingException(
                "A todo without a description? That won't do! Tell me what you need to do.");
        }
        return new TodoCommand(args.trim());
    }

    private DeadlineCommand parseDeadline(String args) throws DingException {
        // format: <desc> /by <when>
        String rest = args.trim();
        int byPos = rest.toLowerCase().indexOf("/by ");
        if (byPos == -1) {
            throw new DingException(
                "I'd love to add that deadline! Just use: deadline <description> /by <when>");
        }

        String desc = rest.substring(0, byPos).trim();
        String by = rest.substring(byPos + 5).trim();

        if (desc.isBlank()) throw new DingException("Your deadline needs a description! What's the task?");
        if (by.isBlank()) throw new DingException("Don't forget when it's due! Add a /by date please.");

        return new DeadlineCommand(desc, by);
    }

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
        String from = rest.substring(fromPos + 7, toPos).trim();
        String to = rest.substring(toPos + 5).trim();

        if (desc.isBlank()) throw new DingException("Your event needs a description! What's happening?");
        if (from.isBlank() || to.isBlank()) {
            throw new DingException(
                "I need to know when your event is! Add /from and /to times please.");
        }

        return new EventCommand(desc, from, to);
    }
}
