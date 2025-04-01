package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class DisplayPreferencesTest {

    @AfterEach
    public void resetDisplayPreferences() {
        // Reset all display preferences to their default values after each test
        DisplayPreferences.resetToDefault();
    }

    @Test
    public void testDefaultPreferences() {
        // Verify default preferences
        assertTrue(DisplayPreferences.isShowName());
        assertTrue(DisplayPreferences.isShowPhone());
        assertTrue(DisplayPreferences.isShowEmail());
        assertTrue(DisplayPreferences.isShowAddress());
        assertTrue(DisplayPreferences.isShowStudentId());
        assertTrue(DisplayPreferences.isShowTags());
        assertTrue(DisplayPreferences.isShowTutorials());
    }

    @Test
    public void testSetShowName() {
        // Test setting and getting showName
        DisplayPreferences.setShowName(false);
        assertFalse(DisplayPreferences.isShowName());
        DisplayPreferences.setShowName(true);
        assertTrue(DisplayPreferences.isShowName());
    }

    @Test
    public void testSetShowPhone() {
        // Test setting and getting showPhone
        DisplayPreferences.setShowPhone(false);
        assertFalse(DisplayPreferences.isShowPhone());
        DisplayPreferences.setShowPhone(true);
        assertTrue(DisplayPreferences.isShowPhone());
    }

    @Test
    public void testSetShowEmail() {
        // Test setting and getting showEmail
        DisplayPreferences.setShowEmail(false);
        assertFalse(DisplayPreferences.isShowEmail());
        DisplayPreferences.setShowEmail(true);
        assertTrue(DisplayPreferences.isShowEmail());
    }

    @Test
    public void testSetShowAddress() {
        // Test setting and getting showAddress
        DisplayPreferences.setShowAddress(false);
        assertFalse(DisplayPreferences.isShowAddress());
        DisplayPreferences.setShowAddress(true);
        assertTrue(DisplayPreferences.isShowAddress());
    }

    @Test
    public void testSetShowStudentId() {
        // Test setting and getting showStudentId
        DisplayPreferences.setShowStudentId(false);
        assertFalse(DisplayPreferences.isShowStudentId());
        DisplayPreferences.setShowStudentId(true);
        assertTrue(DisplayPreferences.isShowStudentId());
    }

    @Test
    public void testSetShowTags() {
        // Test setting and getting showTags
        DisplayPreferences.setShowTags(false);
        assertFalse(DisplayPreferences.isShowTags());
        DisplayPreferences.setShowTags(true);
        assertTrue(DisplayPreferences.isShowTags());
    }

    @Test
    public void testSetShowTutorials() {
        // Test setting and getting showTutorials
        DisplayPreferences.setShowTutorials(false);
        assertFalse(DisplayPreferences.isShowTutorials());
        DisplayPreferences.setShowTutorials(true);
        assertTrue(DisplayPreferences.isShowTutorials());
    }

    @Test
    public void testResetToDefault() {
        // Change all preferences to non-default values
        DisplayPreferences.setShowName(false);
        DisplayPreferences.setShowPhone(false);
        DisplayPreferences.setShowEmail(false);
        DisplayPreferences.setShowAddress(false);
        DisplayPreferences.setShowStudentId(false);
        DisplayPreferences.setShowTags(false);
        DisplayPreferences.setShowTutorials(false);

        // Call resetToDefault
        DisplayPreferences.resetToDefault();

        // Verify that all preferences are reset to their default values
        assertTrue(DisplayPreferences.isShowName());
        assertTrue(DisplayPreferences.isShowPhone());
        assertTrue(DisplayPreferences.isShowEmail());
        assertTrue(DisplayPreferences.isShowAddress());
        assertTrue(DisplayPreferences.isShowStudentId());
        assertTrue(DisplayPreferences.isShowTags());
        assertTrue(DisplayPreferences.isShowTutorials());
    }
}
