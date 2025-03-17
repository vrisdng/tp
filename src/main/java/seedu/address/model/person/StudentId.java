package seedu.address.model.person;

public class StudentId {
    public static final String MESSAGE_CONSTRAINTS = "Student IDs should be numeric and exactly 8 digits long";
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
     */
    public static boolean isValidStudentId(String test) {
        return test.matches("\\d{8}");
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
