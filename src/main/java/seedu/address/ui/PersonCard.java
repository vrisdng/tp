package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label studentId;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane tutorials;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;

        id.setText(displayedIndex + ". ");

        // Always set the text for the name label
        name.setText(person.getName().fullName);
        if (!DisplayPreferences.isShowName()) {
            name.setVisible(false);
        }

        // Always set the text for the phone label
        phone.setText(person.getPhone().value);
        if (!DisplayPreferences.isShowPhone()) {
            phone.setVisible(false);
        }

        // Always set the text for the email label
        email.setText(person.getEmail().value);
        if (!DisplayPreferences.isShowEmail()) {
            email.setVisible(false);
        }

        // Always set the text for the address label
        address.setText(person.getAddress().value);
        if (!DisplayPreferences.isShowAddress()) {
            address.setVisible(false);
        }

        // Always set the text for the studentId label
        studentId.setText(person.getStudentId().value);
        if (!DisplayPreferences.isShowStudentId()) {
            studentId.setVisible(false);
        }

        // Handle tags
        tags.getChildren().clear();
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (!DisplayPreferences.isShowTags()) {
            tags.setVisible(false);
        }

        // Handle tutorials: join tutorial names by commas.
        tutorials.getChildren().clear();
        String tutorialText = person.getTutorials().stream()
                .sorted(Comparator.comparing(tutorial -> tutorial.tutorialName))
                .map(tutorial -> tutorial.tutorialName)
                .collect(Collectors.joining(", "));
        tutorials.getChildren().add(new Label(tutorialText));
        if (!DisplayPreferences.isShowTutorials()) {
            tutorials.setVisible(false);
        }
    }

}
