package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

public class PersonCardTest {

    @BeforeAll
    public static void setUpJavaFx() {
        // Initialize JavaFX toolkit
        Platform.startup(() -> {});
    }

    @Test
    public void display_correctInformation() {
        // Create a sample Person
        Person person = new Person(
            new Name("John Doe"),
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Main Street"),
            new HashSet<>(Set.of(new Tag("friend"))), // Use a mutable set
            new StudentId("A1234567X")
        );

        // Create a PersonCard
        PersonCard personCard = new PersonCard(person, 1);

        // Verify that the PersonCard updates the text dynamically
        assertEquals("1. ", ((Label) personCard.getRoot().lookup("#id")).getText());
        assertTrue(((Label) personCard.getRoot().lookup("#phone")).isVisible() == false);
        assertTrue(((Label) personCard.getRoot().lookup("#email")).isVisible() == false);
        assertTrue(((Label) personCard.getRoot().lookup("#address")).isVisible() == false);
        assertTrue(((Label) personCard.getRoot().lookup("#studentId")).isVisible() == false);
        assertTrue(((FlowPane) personCard.getRoot().lookup("#tags")).isVisible() == false);
    }

    @Test
    public void display_respectsDisplayPreferences() {
        // Create a sample Person
        Person person = new Person(
            new Name("John Doe"),
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Main Street"),
            new HashSet<>(Set.of(new Tag("friend"))), // Use a mutable set
            new StudentId("A1234567X")
        );

        // Set DisplayPreferences to hide some fields
        DisplayPreferences.setShowName(true);
        DisplayPreferences.setShowPhone(false);
        DisplayPreferences.setShowEmail(false);
        DisplayPreferences.setShowAddress(false);
        DisplayPreferences.setShowStudentId(false);
        DisplayPreferences.setShowTags(false);

        // Create a PersonCard
        PersonCard personCard = new PersonCard(person, 1);

        // Verify that the PersonCard respects the DisplayPreferences
        assertEquals("1. ", ((Label) personCard.getRoot().lookup("#id")).getText());
        assertFalse(((Label) personCard.getRoot().lookup("#phone")).isVisible());
        assertFalse(((Label) personCard.getRoot().lookup("#email")).isVisible());
        assertFalse(((Label) personCard.getRoot().lookup("#address")).isVisible());
        assertFalse(((Label) personCard.getRoot().lookup("#studentId")).isVisible());
        assertFalse(((FlowPane) personCard.getRoot().lookup("#tags")).isVisible());
    }
}
