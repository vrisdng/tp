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
    public void parse_validNameSingleKeyword_returnsFindCommand() {
        FindCommand expected = new FindCommand(
                new PersonContainsKeywordsPredicate(PREFIX_NAME.getPrefix(), Arrays.asList("Alice"), false));
        assertParseSuccess(parser, " n/Alice", expected);
    }

    @Test
    public void parse_validNameMultipleKeywords_returnsFindCommand() {
        FindCommand expected = new FindCommand(
                new PersonContainsKeywordsPredicate(PREFIX_NAME.getPrefix(), Arrays.asList("Alice", "Bob"), false));
        assertParseSuccess(parser, " n/Alice n/Bob", expected);
    }

    @Test
    public void parse_validTagMultipleKeywords_returnsFindCommand() {
        FindCommand expected = new FindCommand(
                new PersonContainsKeywordsPredicate(PREFIX_TAG.getPrefix(),
                        Arrays.asList("friends", "owesMoney"), false));
        assertParseSuccess(parser, " t/friends t/owesMoney", expected);
    }

    @Test
    public void parse_validWhitespaceHandled_returnsFindCommand() {
        FindCommand expected = new FindCommand(
                new PersonContainsKeywordsPredicate(PREFIX_NAME.getPrefix(), Arrays.asList("Alice", "Bob"), false));
        assertParseSuccess(parser, " \n  n/Alice   n/Bob  ", expected);
    }

    @Test
    public void parse_multiplePrefixesUsed_throwsParseException() {
        assertParseFailure(parser, " n/Alice t/friends",
                "Please use exactly one field for 'find' command.");
    }

    @Test
    public void parse_invalidPrefixUsed_throwsParseException() {
        assertParseFailure(parser, " x/random", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, " hello n/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
