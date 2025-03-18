package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tutorial(null));
    }

    @Test
    public void constructor_invalidTutorialName_throwsIllegalArgumentException() {
        String invalidTutorialName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tutorial(invalidTutorialName));
    }

    @Test
    public void constructor_validTutorialName_success() {
        String validTutorialName = "CS2103T";
        Tutorial tutorial = new Tutorial(validTutorialName);
        assertEquals(validTutorialName, tutorial.tutorialName);
    }

    @Test
    public void isValidTutorial() {
        // null tutorial name
        assertThrows(NullPointerException.class, () -> Tutorial.isValidTutorial(null));

        // invalid tutorial names
        assertFalse(Tutorial.isValidTutorial("")); // empty string
        assertFalse(Tutorial.isValidTutorial(" ")); // spaces only
        assertFalse(Tutorial.isValidTutorial("CS2103T!")); // special characters
        assertFalse(Tutorial.isValidTutorial("CS 2103T")); // spaces between words

        // valid tutorial names
        assertTrue(Tutorial.isValidTutorial("CS2103T"));
        assertTrue(Tutorial.isValidTutorial("T123"));
        assertTrue(Tutorial.isValidTutorial("COMP1010"));
    }

    @Test
    public void equals() {
        Tutorial tutorial1 = new Tutorial("CS2103T");
        Tutorial tutorial2 = new Tutorial("CS2103T");
        Tutorial tutorial3 = new Tutorial("CS2040S");

        // same object -> returns true
        assertTrue(tutorial1.equals(tutorial1));

        // same values -> returns true
        assertTrue(tutorial1.equals(tutorial2));

        // different values -> returns false
        assertFalse(tutorial1.equals(tutorial3));

        // null -> returns false
        assertFalse(tutorial1.equals(null));

        // different object type -> returns false
        assertFalse(tutorial1.equals("CS2103T"));
    }

    @Test
    public void hashCode_test() {
        Tutorial tutorial1 = new Tutorial("CS2103T");
        Tutorial tutorial2 = new Tutorial("CS2103T");

        // same values -> same hash code
        assertEquals(tutorial1.hashCode(), tutorial2.hashCode());
    }

    @Test
    public void toString_test() {
        Tutorial tutorial = new Tutorial("CS2103T");
        assertEquals("[CS2103T]", tutorial.toString());
    }
}
