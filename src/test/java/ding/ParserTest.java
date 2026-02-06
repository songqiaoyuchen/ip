package ding;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ding.commands.Command;
import ding.commands.DeadlineCommand;
import ding.commands.DeleteCommand;
import ding.commands.EventCommand;
import ding.commands.ExitCommand;
import ding.commands.ListCommand;
import ding.commands.MarkCommand;
import ding.commands.TodoCommand;
import ding.commands.UnmarkCommand;
import ding.exceptions.DingException;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    @DisplayName("null input throws DingException")
    void parse_nullInput_throws() {
        DingException ex = assertThrows(DingException.class, () -> parser.parse(null));
        assertTrue(ex.getMessage().contains("waiting for your command"));
    }

    @Test
    @DisplayName("empty input throws DingException")
    void parse_emptyInput_throws() {
        DingException ex = assertThrows(DingException.class, () -> parser.parse("   \t  "));
        assertTrue(ex.getMessage().contains("waiting for your command"));
    }

    @Test
    @DisplayName("unknown keyword throws DingException")
    void parseUnknownKeyword_throws() {
        DingException ex = assertThrows(DingException.class, () -> parser.parse("foobar"));
        assertTrue(ex.getMessage().contains("don't understand"));
    }

    @Test
    @DisplayName("bye returns ExitCommand and isExit=true")
    void parseBye_returnsExitCommand() throws DingException {
        Command cmd = parser.parse("bye");
        assertInstanceOf(ExitCommand.class, cmd);
        assertTrue(cmd.isExit());
    }

    @Test
    @DisplayName("list returns ListCommand")
    void parseList_returnsListCommand() throws DingException {
        Command cmd = parser.parse("list");
        assertInstanceOf(ListCommand.class, cmd);
        assertFalse(cmd.isExit());
    }

    @Test
    @DisplayName("mark requires index")
    void parseMark_missingIndex_throws() {
        DingException ex = assertThrows(DingException.class, () -> parser.parse("mark"));
        assertTrue(ex.getMessage().toLowerCase().contains("task number"));
    }

    @Test
    @DisplayName("mark invalid index format throws")
    void parseMark_invalidIndex_throws() {
        DingException ex = assertThrows(DingException.class, () -> parser.parse("mark abc"));
        assertTrue(ex.getMessage().toLowerCase().contains("valid task number"));
    }

    @Test
    @DisplayName("mark returns MarkCommand")
    void parseMark_valid_returnsMarkCommand() throws DingException {
        Command cmd = parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, cmd);
    }

    @Test
    @DisplayName("unmark returns UnmarkCommand")
    void parseUnmark_valid_returnsUnmarkCommand() throws DingException {
        Command cmd = parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, cmd);
    }

    @Test
    @DisplayName("delete returns DeleteCommand")
    void parseDelete_valid_returnsDeleteCommand() throws DingException {
        Command cmd = parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, cmd);
    }

    @Test
    @DisplayName("todo requires description")
    void parseTodo_missingDesc_throws() {
        DingException ex = assertThrows(DingException.class, () -> parser.parse("todo   \t "));
        assertTrue(ex.getMessage().toLowerCase().contains("description"));
    }

    @Test
    @DisplayName("todo returns TodoCommand")
    void parseTodo_valid_returnsTodoCommand() throws DingException {
        Command cmd = parser.parse("todo buy milk");
        assertInstanceOf(TodoCommand.class, cmd);
    }

    @Test
    @DisplayName("deadline missing /by throws")
    void parseDeadline_missingBy_throws() {
        DingException ex = assertThrows(DingException.class, () -> parser.parse("deadline report"));
        assertTrue(ex.getMessage().toLowerCase().contains("deadline"));
    }

    @Test
    @DisplayName("deadline returns DeadlineCommand for accepted date formats")
    void parseDeadline_valid_returnsDeadlineCommand() throws DingException {
        String[] inputs = new String[] {
            "deadline submit report /by 2019-12-02 1800",
            "deadline submit report /by 2/12/2019 1800",
            "deadline submit report /by 2-12-2019 1800",
            "deadline submit report /by 2019-12-02",
            "deadline submit report /by 2/12/2019",
            "deadline submit report /by 2-12-2019"
        };
        for (String in : inputs) {
            Command cmd = parser.parse(in);
            assertInstanceOf(DeadlineCommand.class, cmd, "Expected DeadlineCommand for input: " + in);
        }
    }

    @Test
    @DisplayName("event missing fields throws")
    void parseEvent_missingFields_throws() {
        DingException ex1 = assertThrows(DingException.class, () -> parser.parse("event party /from 2019-12-02 1800"));
        assertTrue(ex1.getMessage().toLowerCase().contains("event"));
        DingException ex2 = assertThrows(DingException.class, () -> parser.parse("event party /to 2019-12-02 2000"));
        assertTrue(ex2.getMessage().toLowerCase().contains("event"));
    }

    @Test
    @DisplayName("event returns EventCommand for valid input")
    void parseEvent_valid_returnsEventCommand() throws DingException {
        Command cmd = parser.parse("event project meeting /from 2019-12-02 1800 /to 2019-12-02 2000");
        assertInstanceOf(EventCommand.class, cmd);
    }
}
