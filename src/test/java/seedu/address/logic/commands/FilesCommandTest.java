package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FilesCommandTest {

    @Test
    public void execute_saveCommand_createsFile() throws Exception {
        // Setup
        Model model = new ModelManager();
        UserPrefs userPrefs = new UserPrefs();
        Path tempDirectory = Files.createTempDirectory("testFilesCommand");
        userPrefs.setAddressBookFilePath(tempDirectory.resolve("addressbook.json"));
        Path expectedFilePath = tempDirectory.resolve("test.json");
        Files.createFile(expectedFilePath);
        model.setUserPrefs(userPrefs);
        FilesCommand filesCommand = new FilesCommand();

        // Execute
        CommandResult commandResult = filesCommand.execute(model);

        // Verify
        String expectedOutput = "Files in data directory:\n"
            + expectedFilePath.getFileName().toString().replace(".json", "");
        assertEquals(expectedOutput, commandResult.getFeedbackToUser());

        // Cleanup
        Files.deleteIfExists(expectedFilePath);
        Files.deleteIfExists(tempDirectory);
    }
}
