package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.ui.DisplayPreferences;

public class LoadCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LoadCommandTest");
    private static final Path VALID_FILE_PATH = TEST_DATA_FOLDER.resolve("dummy.json");
    private static final Path INVALID_FILE_PATH = TEST_DATA_FOLDER.resolve("nonExistentFile.json");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validFile_success() throws Exception {
        // Arrange
        String fileName = "dummy";
        LoadCommand loadCommand = new LoadCommand(fileName);
        JsonAddressBookStorage storage = new JsonAddressBookStorage(VALID_FILE_PATH);
        AddressBook expectedAddressBook = new AddressBook(storage.readAddressBook(VALID_FILE_PATH).orElseThrow());
        Model expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        // Act
        CommandResult result = loadCommand.execute(model);

        // Assert
        assertEquals(String.format(LoadCommand.MESSAGE_SUCCESS, fileName), result.getFeedbackToUser());
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
        assertDefaultDisplayPreferences();
    }

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
    public void execute_resetsDisplayPreferences() throws Exception {
        // Arrange
        String fileName = "dummy";
        LoadCommand loadCommand = new LoadCommand(fileName);

        // Simulate custom display preferences
        DisplayPreferences.setShowName(false);
        DisplayPreferences.setShowPhone(false);
        DisplayPreferences.setShowEmail(false);
        DisplayPreferences.setShowAddress(false);
        DisplayPreferences.setShowStudentId(false);
        DisplayPreferences.setShowTags(false);
        DisplayPreferences.setShowTutorials(false);

        // Act
        loadCommand.execute(model);

        // Assert
        assertDefaultDisplayPreferences();
    }

    /**
     * Asserts that the display preferences have been reset to their default state.
     */
    private void assertDefaultDisplayPreferences() {
        assertEquals(true, DisplayPreferences.isShowName());
        assertEquals(true, DisplayPreferences.isShowPhone());
        assertEquals(true, DisplayPreferences.isShowEmail());
        assertEquals(true, DisplayPreferences.isShowAddress());
        assertEquals(true, DisplayPreferences.isShowStudentId());
        assertEquals(true, DisplayPreferences.isShowTags());
        assertEquals(true, DisplayPreferences.isShowTutorials());
    }
}
