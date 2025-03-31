package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonSerializableAddressBook;
import seedu.address.ui.DisplayPreferences;
import seedu.address.commons.util.JsonUtil;

public class LoadCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // @Test
    // public void execute_validFile_success() throws Exception {
    //     // Arrange
    //     String fileName = "dummy";
    //     LoadCommand loadCommand = new LoadCommand(fileName);

    //     // Use the same data for both the temporary JSON file and the expected AddressBook
    //     AddressBook testAddressBook = getTypicalAddressBook();
    //     Path tempFilePath = createTempJsonFile(testAddressBook);

    //     JsonAddressBookStorage storage = new JsonAddressBookStorage(tempFilePath);
    //     AddressBook expectedAddressBook = new AddressBook(storage.readAddressBook(tempFilePath).orElseThrow());
    //     Model expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

    //     // Act
    //     CommandResult result = loadCommand.execute(model);

    //     // Assert
    //     assertEquals(String.format(LoadCommand.MESSAGE_SUCCESS, fileName), result.getFeedbackToUser());
    //     assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    //     assertDefaultDisplayPreferences();
    // }

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

        // Create in-memory JSON data for the test
        AddressBook testAddressBook = getTypicalAddressBook();
        Path tempFilePath = createTempJsonFile(testAddressBook);

        JsonAddressBookStorage storage = new JsonAddressBookStorage(tempFilePath);
        AddressBook expectedAddressBook = new AddressBook(storage.readAddressBook(tempFilePath).orElseThrow());
        model.setAddressBook(expectedAddressBook);

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

    /**
     * Creates a temporary JSON file with the given address book data.
     *
     * @param addressBook The address book data to write to the file.
     * @return The path to the temporary JSON file.
     * @throws IOException If an error occurs while creating the file.
     */
    private Path createTempJsonFile(AddressBook addressBook) throws IOException {
        Path tempFilePath = Path.of("tempAddressBook.json");
        JsonSerializableAddressBook jsonAddressBook = new JsonSerializableAddressBook(addressBook);
        JsonUtil.saveJsonFile(jsonAddressBook, tempFilePath);
        return tempFilePath;
    }
}
