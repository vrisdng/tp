package seedu.address.logic.parser;

import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LoadCommand object.
 */
public class LoadCommandParser implements Parser<LoadCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LoadCommand
     * and returns a LoadCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public LoadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException("File path cannot be empty.");
        }
        return new LoadCommand(trimmedArgs);
    }
}
