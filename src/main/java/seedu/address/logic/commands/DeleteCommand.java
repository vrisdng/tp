package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the Student ID used in the displayed students list.\n"
            + "Parameters: Student ID (Student IDs should start with 'A' followed by 7 digits and a letter.)\n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student with ID %1$s not found!";

    private final StudentId targetStudentId;

    public DeleteCommand(StudentId targetStudentId) {
        this.targetStudentId = targetStudentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Find the person with the matching StudentId.
        Person personToDelete = lastShownList.stream()
                .filter(person -> person.getStudentId().equals(targetStudentId))
                .findFirst()
                .orElse(null);

        if (personToDelete == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, targetStudentId));
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

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
        return targetStudentId.equals(otherDeleteCommand.targetStudentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetStudentId", targetStudentId)
                .toString();
    }
}
