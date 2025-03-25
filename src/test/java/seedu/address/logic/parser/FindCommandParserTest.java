package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Test for finding by name
        FindCommand expectedFindCommand = new FindCommand(
                new PersonContainsKeywordsPredicate(PREFIX_NAME.getPrefix(), Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, PREFIX_NAME.getPrefix() + " Alice Bob", expectedFindCommand);

        // Test for finding by tag
        FindCommand expectedTagFindCommand = new FindCommand(
                new PersonContainsKeywordsPredicate(PREFIX_TAG.getPrefix(), Arrays.asList("friends", "owesMoney")));
        assertParseSuccess(parser, PREFIX_TAG.getPrefix() + " friends owesMoney", expectedTagFindCommand);

        // Test with multiple whitespaces between keywords
        FindCommand expectedWhitespaceFindCommand = new FindCommand(
                new PersonContainsKeywordsPredicate(PREFIX_NAME.getPrefix(), Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " \n " + PREFIX_NAME.getPrefix() + " Alice \n \t Bob  \t",
                expectedWhitespaceFindCommand);
    }
}
