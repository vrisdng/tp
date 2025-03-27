package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.JsonAddressBookStorage;

import java.nio.file.Path;


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
    public static final String USERPREFS_DIRECTORY = "userprefs";

    private final String fileName;

    public LoadCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Path dataFolderPath = model.getUserPrefs().getAddressBookFilePath().getParent();
        Path filePath = dataFolderPath.resolve(fileName + ".json");        

        // Logic to load the file can be added here
        if (!filePath.toFile().exists()) {
            throw new CommandException(String.format(MESSAGE_FILE_NOT_FOUND, fileName));
        }

        JsonAddressBookStorage storage = new JsonAddressBookStorage(filePath);
        try {
            ReadOnlyAddressBook loadedAddressBook = storage.readAddressBook(filePath).orElseThrow(() -> 
                new CommandException("Failed to load address book from file."));
            System.out.println("Loaded address book: " + loadedAddressBook);
            model.setAddressBook(new AddressBook(loadedAddressBook));
            
        } catch (Exception e) {
            throw new CommandException("Error reading file: " + e.getMessage());
        }

        // For now, we just simulate a successful load
        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoadCommand // instanceof handles nulls
                && fileName.equals(((LoadCommand) other).fileName)); // state check
    }
}
