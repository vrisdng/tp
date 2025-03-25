package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD 
        + ": Finds all students whose specified field contains any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: PREFIX/KEYWORD [MORE_KEYWORDS]...\n"
        + "Supported prefixes: "
        + PREFIX_NAME.getPrefix() + " (name), "
        + PREFIX_TAG.getPrefix() + " (tag), "
        + PREFIX_PHONE.getPrefix() + " (phone), "
        + PREFIX_EMAIL.getPrefix() + " (email), "
        + PREFIX_ADDRESS.getPrefix() + " (address), "
        + PREFIX_STUDENT_ID.getPrefix() + " (id), "
        + PREFIX_TUTORIAL.getPrefix() + " (tutorial)\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_NAME.getPrefix() + " " + "john\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_TAG.getPrefix() + " " + "owesMoney\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_TUTORIAL.getPrefix() + " " + "CS2103T";



    private final PersonContainsKeywordsPredicate predicate;

    public FindCommand(PersonContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
