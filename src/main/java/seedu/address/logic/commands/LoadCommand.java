package seedu.address.logic.commands;

import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.ui.DisplayPreferences;
import seedu.address.ui.UiManager;

/**
 * Loads a file from the userprefs directory.
 */
public class LoadCommand extends Command {

    public static final String COMMAND_WORD = "load";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Loads a file from the userprefs directory.\n"
            + "Parameters: FILENAME\n"
            + "Example: " + COMMAND_WORD + " addressbook.json";

    public static final String MESSAGE_SUCCESS = "File loaded successfully: %s";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found in the userprefs directory: %s";

    private final String fileName;

    public LoadCommand(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Path dataFolderPath = model.getUserPrefs().getAddressBookFilePath().getParent();
        Path filePath = dataFolderPath.resolve(fileName + ".json");

        // Logic to load the file
        if (!filePath.toFile().exists()) {
            throw new CommandException(String.format(MESSAGE_FILE_NOT_FOUND, fileName));
        }

        JsonAddressBookStorage storage = new JsonAddressBookStorage(filePath);
        try {
            ReadOnlyAddressBook loadedAddressBook = storage.readAddressBook(filePath).orElseThrow(() ->
                new CommandException("Failed to load address book from file."));
            model.setAddressBook(new AddressBook(loadedAddressBook));
        } catch (Exception e) {
            throw new CommandException("Error reading file: " + e.getMessage());
        }

        // Reset display preferences to default state
        DisplayPreferences.resetToDefault();

        // Refresh the UI to reflect the default display preferences
        UiManager.refreshPersonListPanel();

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoadCommand // instanceof handles nulls
                && fileName.equals(((LoadCommand) other).fileName)); // state check
    }
}
