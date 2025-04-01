package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split("/", 2);

        // Ensure the input has both a field and a keyword
        if (splitArgs.length < 2 || splitArgs[1].isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        String field = splitArgs[0].trim() + "/";
        String keyword = splitArgs[1].trim();

        // Validate the field
        if (!isValidField(field)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        return new DeleteCommand(field, keyword);
    }

    /**
     * Checks if the given field is valid.
     *
     * @param field The field to validate.
     * @return {@code true} if the field is valid, {@code false} otherwise.
     */
    private boolean isValidField(String field) {
        // Only support the following fields for deletion
        // - Name (n/)
        // - Email (e/)
        // - Student ID (s/)
        // - Tutorial (t/)
        return field.equals(PREFIX_NAME.getPrefix())
                || field.equals(PREFIX_TAG.getPrefix())
                || field.equals(PREFIX_EMAIL.getPrefix())
                || field.equals(PREFIX_STUDENT_ID.getPrefix())
                || field.equals(PREFIX_TUTORIAL.getPrefix());
    }
}
