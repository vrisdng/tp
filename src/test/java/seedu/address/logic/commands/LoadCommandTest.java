package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LoadCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidFile_throwsCommandException() {
        // Arrange
        String fileName = "nonExistentFile";
        LoadCommand loadCommand = new LoadCommand(fileName);

        // Act & Assert
        assertCommandFailure(loadCommand, model,
                String.format(LoadCommand.MESSAGE_FILE_NOT_FOUND, fileName));
    }

    @Test
    public void execute_invalidFileWithJsonExtension_throwsCommandException() {
        // Arrange
        String fileName = "nonExistentFile.json";
        LoadCommand loadCommand = new LoadCommand(fileName);

        // Act & Assert
        assertCommandFailure(loadCommand, model,
                String.format(LoadCommand.MESSAGE_FILE_NOT_FOUND, fileName));
    }
}
