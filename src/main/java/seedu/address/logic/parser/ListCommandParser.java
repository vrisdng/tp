package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    // Valid fields for the list command
    private static final Set<String> VALID_FIELDS = new HashSet<>(Arrays.asList(
            "name", "phone", "email", "address", "id", "tags"
    ));

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // Default to showing everything if no arguments are provided
        boolean showName = true;
        boolean showPhone = true;
        boolean showEmail = true;
        boolean showAddress = true;
        boolean showStudentId = true;
        boolean showTags = true;

        if (!trimmedArgs.isEmpty()) {
            // Split arguments into tokens
            String[] tokens = trimmedArgs.split("\\s+");

            // Validate each token
            for (String token : tokens) {
                if (!VALID_FIELDS.contains(token)) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
                }
            }

            // Parse arguments to determine which fields to show
            showName = Arrays.asList(tokens).contains("name");
            showPhone = Arrays.asList(tokens).contains("phone");
            showEmail = Arrays.asList(tokens).contains("email");
            showAddress = Arrays.asList(tokens).contains("address");
            showStudentId = Arrays.asList(tokens).contains("id");
            showTags = Arrays.asList(tokens).contains("tags");
        }


        return new ListCommand(showName, showPhone, showEmail, showAddress, showStudentId, showTags);
    }
}
