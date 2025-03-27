package seedu.address.logic.parser;

import seedu.address.logic.commands.FilesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilesCommand object
 */
public class FilesCommandParser implements Parser<FilesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilesCommand
     * and returns a FilesCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public FilesCommand parse(String args) throws ParseException {
        // Currently, FilesCommand does not take any arguments.
        // If arguments are needed in the future, parse them here.
        return new FilesCommand();
    }
}
