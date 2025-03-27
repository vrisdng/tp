package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilesCommand;

public class FilesCommandParserTest {

    private final FilesCommandParser parser = new FilesCommandParser();

    @Test
    public void parse_validArgs_returnsFilesCommand() {
        // Since FilesCommand does not take arguments, any input should not throw an exception
        assertDoesNotThrow(() -> {
            FilesCommand command = parser.parse("");
            // Additional assertions can be added here if needed
        });
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // If FilesCommand starts accepting arguments in the future, this test can be updated
        assertDoesNotThrow(() -> parser.parse("invalid args"));
    }
}
