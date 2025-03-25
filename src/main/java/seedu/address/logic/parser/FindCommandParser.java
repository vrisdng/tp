package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split("\\s+", 2);
        if (splitArgs.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String field = splitArgs[0];
        String[] keywords = splitArgs[1].split("\\s+");

        // Validate the field
        if (!isValidField(field)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new PersonContainsKeywordsPredicate(field, Arrays.asList(keywords)));
    }

    /**
     * Checks if the given field is a valid prefix.
     */
    private boolean isValidField(String field) {
        return field.equals(PREFIX_NAME.getPrefix())
                || field.equals(PREFIX_TAG.getPrefix())
                || field.equals(PREFIX_PHONE.getPrefix())
                || field.equals(PREFIX_EMAIL.getPrefix())
                || field.equals(PREFIX_ADDRESS.getPrefix())
                || field.equals(PREFIX_STUDENT_ID.getPrefix())
                || field.equals(PREFIX_TUTORIAL.getPrefix());
    }
}
