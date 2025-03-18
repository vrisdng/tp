package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import seedu.address.ui.DisplayPreferences;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        // Default behavior: all fields are displayed
        ListCommand listCommand = new ListCommand(true, true, true, true, true, true);
        assertCommandSuccess(listCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);

        // Verify DisplayPreferences are updated correctly
        assert DisplayPreferences.isShowName();
        assert DisplayPreferences.isShowPhone();
        assert DisplayPreferences.isShowEmail();
        assert DisplayPreferences.isShowAddress();
        assert DisplayPreferences.isShowStudentId();
        assert DisplayPreferences.isShowTags();
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        // Filter the list to show only the first person
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Execute the command to reset the filter and show all persons
        ListCommand listCommand = new ListCommand(true, true, true, true, true, true);
        assertCommandSuccess(listCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);

        // Verify DisplayPreferences are updated correctly
        assert DisplayPreferences.isShowName();
        assert DisplayPreferences.isShowPhone();
        assert DisplayPreferences.isShowEmail();
        assert DisplayPreferences.isShowAddress();
        assert DisplayPreferences.isShowStudentId();
        assert DisplayPreferences.isShowTags();
    }

    @Test
    public void execute_listWithSpecificFields_showsFilteredFields() {
        // Test with specific fields (e.g., name and phone only)
        ListCommand listCommand = new ListCommand(true, true, false, false, false, false);
        assertCommandSuccess(listCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);

        // Verify DisplayPreferences are updated correctly
        assert DisplayPreferences.isShowName();
        assert DisplayPreferences.isShowPhone();
        assert !DisplayPreferences.isShowEmail();
        assert !DisplayPreferences.isShowAddress();
        assert !DisplayPreferences.isShowStudentId();
        assert !DisplayPreferences.isShowTags();
    }
}
