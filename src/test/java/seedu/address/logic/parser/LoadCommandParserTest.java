package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LoadCommandParserTest {

    private final LoadCommandParser parser = new LoadCommandParser();

    @Test
    public void parse_validFileName_success() throws Exception {
        String validFileName = "file.txt";
        LoadCommand command = parser.parse(validFileName);
        assertEquals(validFileName, command.getFileName());
    }

    @Test
    public void parse_emptyFileName_throwsParseException() {
        String emptyFileName = "   ";
        assertThrows(ParseException.class, () -> parser.parse(emptyFileName));
    }

    @Test
    public void parse_nullFileName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_fileNameWithSpaces_success() throws Exception {
        String fileNameWithSpaces = "  file.txt  ";
        LoadCommand command = parser.parse(fileNameWithSpaces);
        assertEquals("file.txt", command.getFileName());
    }

    @Test
    public void parse_invalidCharactersInFileName_success() throws Exception {
        String invalidFileName = "invalid|file.txt";
        LoadCommand command = parser.parse(invalidFileName);
        assertEquals(invalidFileName, command.getFileName());
    }
}
