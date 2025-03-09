package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using their email from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the email used in the displayed person list.\n"
            + "Parameters: EMAIL (must be a valid email)\n"
            + "Example: " + COMMAND_WORD + " johndoe@example.com";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "No person found with the given email.";

    private final Email targetEmail;

    public DeleteCommand(Email targetEmail) {
        this.targetEmail = targetEmail;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToDelete = lastShownList.stream()
                .filter(person -> person.getEmail().equals(targetEmail))
                .findFirst()
                .orElseThrow(() -> new CommandException(MESSAGE_PERSON_NOT_FOUND));

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
        return targetEmail.equals(otherDeleteCommand.targetEmail);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetEmail", targetEmail)
                .toString();
    }
}
