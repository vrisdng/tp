package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;

/**
 * Contains unit tests for {@code DeleteCommandParser}.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        // Test for deleting by student ID
        String validStudentId = "A1234567X";
        assertParseSuccess(parser, "s/ " + validStudentId, new DeleteCommand("s/", validStudentId));

        // Test for deleting by tutorial
        String validTutorial = "CS2103T";
        assertParseSuccess(parser, "tut/ " + validTutorial, new DeleteCommand("tut/", validTutorial));

        // Test for deleting by name
        String validName = "Alice";
        assertParseSuccess(parser, "n/ " + validName, new DeleteCommand("n/", validName));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Missing prefix
        assertParseFailure(parser, "A1234567X",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // Invalid prefix
        assertParseFailure(parser, "invalid/ keyword",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // Missing keyword
        assertParseFailure(parser, "s/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
