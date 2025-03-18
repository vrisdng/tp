package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_noArguments_success() {
        // No arguments provided, default to showing all fields
        assertParseSuccess(parser, "",
                new ListCommand(true, true, true, true, true, true));
    }

    @Test
    public void parse_allValidFields_success() {
        // All valid fields provided
        assertParseSuccess(parser, "name phone email address id tags",
                new ListCommand(true, true, true, true, true, true));
    }

    @Test
    public void parse_someValidFields_success() {
        // Only some valid fields provided
        assertParseSuccess(parser, "name email tags",
                new ListCommand(true, false, true, false, false, true));
    }

    @Test
    public void parse_invalidField_failure() {
        // Invalid field provided
        assertParseFailure(parser, "invalidField",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_mixedValidAndInvalidFields_failure() {
        // Mixed valid and invalid fields
        assertParseFailure(parser, "name invalidField",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicateFields_success() {
        // Duplicate valid fields should still succeed
        assertParseSuccess(parser, "name name email email",
                new ListCommand(true, false, true, false, false, false));
    }

    @Test
    public void parse_whitespaceOnly_success() {
        // Input with only whitespace should default to showing all fields
        assertParseSuccess(parser, "   ",
                new ListCommand(true, true, true, true, true, true));
    }
}
