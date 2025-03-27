package seedu.address.logic.commands;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all files in the data directory excluding addressbook.json.
 */
public class FilesCommand extends Command {

    public static final String COMMAND_WORD = "files";
    public static final String MESSAGE_SUCCESS = "Files in data directory:\n%s";
    public static final String MESSAGE_NO_FILES = "No files found in the data directory.";

    private static final String EXCLUDED_FILE = "addressbook.json";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model must not be null";
        Path dataFolderPath = model.getUserPrefs().getAddressBookFilePath().getParent();
        assert dataFolderPath != null : "Data folder path should not be null";

        File dataFolder = dataFolderPath.toFile();
        File[] files = dataFolder.listFiles();

        if (files == null) {
            throw new CommandException("Error reading data directory.");
        }

        String filesList = Arrays.stream(files)
            .filter(file -> !file.getName().equals(EXCLUDED_FILE))
            .map(File::getName)
            .filter(name -> name.endsWith(".json"))
            .map(name -> name.replace(".json", ""))
            .collect(Collectors.joining("\n"));

        if (filesList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_FILES);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, filesList));
    }
}
