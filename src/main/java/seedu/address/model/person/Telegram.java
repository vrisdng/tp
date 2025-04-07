package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's Telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {
    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handles should be 5 to 32 characters long and can only "
                    + "contain alphanumeric characters and underscores.\n"
                    + "Cannot start with an underscore.\n"
                    + "You do not need to include '@'.";
    public final String value;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid telegram handle.
     */
    public Telegram(String telegram) {
        if (!isValidTelegram(telegram)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = telegram;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
     * A valid telegram handle should be 5 to 32 characters long and can only contain
     * alphanumeric characters and underscores.
     */
    public static boolean isValidTelegram(String test) {
        requireNonNull(test);
        return test.isEmpty() || test.matches("^[A-Za-z0-9_]{5,32}$") && !test.startsWith("_");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getDisplayValue() {
        return value.isEmpty() ? "" : "@" + value;
    }
}
