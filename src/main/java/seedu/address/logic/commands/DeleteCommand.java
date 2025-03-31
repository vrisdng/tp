package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.ui.UiManager;

/**
 * Deletes students based on a specified field and value.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes students based on the specified field and value.\n"
        + "Parameters: PREFIX KEYWORD\n"
        + "Supported prefixes: "
        + "n/ (Name), "
        + "t/ (Tag), "
        + "p/ (Phone), "
        + "e/ (Email), "
        + "a/ (Address), "
        + "s/ (Student ID), "
        + "tut/ (Tutorial)\n"
        + "Example: " + COMMAND_WORD + " n/ Alice\n"
        + "Example: " + COMMAND_WORD + " t/ friends\n"
        + "Example: " + COMMAND_WORD + " s/ A1234567X\n"
        + "Example: " + COMMAND_WORD + " tut/ CS2103T";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Students: %1$s";
    public static final String MESSAGE_NO_PERSON_FOUND = "No students found matching the criteria: %1$s";

    private final String field;
    private final String keyword;

    /**
     * Constructs a {@code DeleteCommand}.
     *
     * @param field The field to search by (e.g., name, tag, phone, etc.).
     * @param keyword The keyword to match in the specified field.
     */
    public DeleteCommand(String field, String keyword) {
        this.field = field;
        this.keyword = keyword;
    }

    /**
     * Executes the delete command by finding and deleting all persons matching the specified field and keyword.
     *
     * @param model The model containing the list of persons.
     * @return A {@code CommandResult} indicating the number of persons deleted.
     * @throws CommandException If no persons match the specified field and keyword.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Create a predicate to find matching persons
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(field, List.of(keyword));
        List<Person> personsToDelete = lastShownList.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        if (personsToDelete.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NO_PERSON_FOUND, field + " " + keyword));
        }

        // Delete all matching persons
        for (Person person : personsToDelete) {
            model.deletePerson(person);
        }

        UiManager.refreshPersonListPanel(); // Update UI after deletion

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personsToDelete.size()));
    }

    /**
     * Checks if this command is equal to another object.
     *
     * @param other The other object to compare to.
     * @return {@code true} if the other object is a {@code DeleteCommand} with the same field and keyword.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return field.equals(otherDeleteCommand.field) && keyword.equals(otherDeleteCommand.keyword);
    }

    /**
     * Returns a string representation of this command.
     *
     * @return A string representation of the command, including the field and keyword.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("field", field)
                .add("keyword", keyword)
                .toString();
    }
}
