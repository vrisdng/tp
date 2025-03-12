package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a tutorial in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTutorialName(String)}
 */
public class Tutorial {

    public static final String MESSAGE_CONSTRAINTS = "Tutorials names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tutorialName;

    /**
     * Constructs a {@code tutorial}.
     *
     * @param tutorialName A valid tutorial name.
     */
    public Tutorial(String tutorialName) {
        requireNonNull(tutorialName);
        checkArgument(isValidTutorialName(tutorialName), MESSAGE_CONSTRAINTS);
        this.tutorialName = tutorialName;
    }

    /**
     * Returns true if a given string is a valid tutorial name.
     */
    public static boolean isValidTutorialName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tutorial)) {
            return false;
        }

        Tutorial otherTutorial = (Tutorial) other;
        return tutorialName.equals(otherTutorial.tutorialName);
    }

    @Override
    public int hashCode() {
        return tutorialName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tutorialName + ']';
    }

}
