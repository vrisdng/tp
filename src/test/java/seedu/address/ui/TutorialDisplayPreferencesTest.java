package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TutorialDisplayPreferencesTest {

    @AfterEach
    public void resetDisplayPreferences() {
        // Reset all display preferences to their default values after each test
        DisplayPreferences.setShowTutorials(true);
    }

    @Test
    public void testDefaultTutorialPreferences() {
        // Verify default tutorial preferences
        assertTrue(DisplayPreferences.isShowTutorials());
    }

    @Test
    public void testSetShowTutorials() {
        // Test setting and getting showTutorials
        DisplayPreferences.setShowTutorials(false);
        assertFalse(DisplayPreferences.isShowTutorials());
        DisplayPreferences.setShowTutorials(true);
        assertTrue(DisplayPreferences.isShowTutorials());
    }
}
