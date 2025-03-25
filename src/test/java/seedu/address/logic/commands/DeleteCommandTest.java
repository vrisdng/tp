package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("n/", personToDelete.getName().fullName);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTagUnfilteredList_success() {
        // Get the tag of the first person in the list
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String tagToDelete = personToDelete.getTags().iterator().next().tagName; // Get the first tag

        // Create the DeleteCommand
        DeleteCommand deleteCommand = new DeleteCommand("t/", tagToDelete);

        // Count the number of persons with the matching tag
        long matchingPersonsCount = model.getFilteredPersonList().stream()
                .filter(person -> person.getTags().stream()
                        .anyMatch(tag -> tag.tagName.equals(tagToDelete)))
                .count();

        // Update the expected message to reflect the number of deletions
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, matchingPersonsCount);

        // Create the expected model
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.getFilteredPersonList().stream()
                .filter(person -> person.getTags().stream()
                        .anyMatch(tag -> tag.tagName.equals(tagToDelete)))
                .forEach(expectedModel::deletePerson);

        // Assert that the command succeeds and deletes all matching persons
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validStudentIdFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("s/", personToDelete.getStudentId().value);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, 1);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIdFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        DeleteCommand deleteCommand = new DeleteCommand("s/", "A0000000Z");

        assertCommandFailure(deleteCommand, model,
                String.format(DeleteCommand.MESSAGE_NO_PERSON_FOUND, "s/ A0000000Z"));
    }

    @Test
    public void equals() {
        DeleteCommand deleteNameCommand = new DeleteCommand("n/", "Alice");
        DeleteCommand deleteTagCommand = new DeleteCommand("t/", "friends");

        // same object -> returns true
        assertTrue(deleteNameCommand.equals(deleteNameCommand));

        // same values -> returns true
        DeleteCommand deleteNameCommandCopy = new DeleteCommand("n/", "Alice");
        assertTrue(deleteNameCommand.equals(deleteNameCommandCopy));

        // different types -> returns false
        assertFalse(deleteNameCommand.equals(1));

        // null -> returns false
        assertFalse(deleteNameCommand.equals(null));

        // different commands -> returns false
        assertFalse(deleteNameCommand.equals(deleteTagCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteCommand deleteCommand = new DeleteCommand("n/", "Alice");
        String expected = DeleteCommand.class.getCanonicalName() + "{field=n/, keyword=Alice}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
