package seedu.address.model.person;

/**
 * Represents a Student's ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {
    public static final String MESSAGE_CONSTRAINTS =
            "Student IDs should start with 'A' followed by 7 digits and a letter.";
    public final String value;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid student ID.
     */
    public StudentId(String studentId) {
        if (!isValidStudentId(studentId)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = studentId;
    }

    /**
     * Returns true if a given string is a valid student ID.
     * This program assumes that the student is from NUS so the student ID must start with 'A'.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches("A\\d{7}[A-Z]");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
