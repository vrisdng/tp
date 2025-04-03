package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests whether a {@code Person}'s specified field matches any of the given keywords.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final String field;
    private final List<String> keywords;
    private final boolean isDeleteOperation;

    /**
     * Constructs a {@code PersonContainsKeywordsPredicate}.
     *
     * @param field The field to be searched (e.g., name, email, phone, etc.).
     * @param keywords The list of keywords to search for.
     * @param isDeleteOperation Whether this predicate is for a delete operation.
     */
    public PersonContainsKeywordsPredicate(String field, List<String> keywords, boolean isDeleteOperation) {
        this.field = field;
        this.keywords = keywords;
        this.isDeleteOperation = isDeleteOperation;
    }

    @Override
    public boolean test(Person person) {
        if (field.equalsIgnoreCase(PREFIX_NAME.getPrefix())) {
            if (isDeleteOperation) {
                // For delete, match all keywords in the name field
                return StringUtil.containsAllWordsIgnoreCase(person.getName().fullName, keywords);
            } else {
                // For find, match any keyword in the name field
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getName().fullName, keyword));
            }
        } else if (field.equalsIgnoreCase(PREFIX_TAG.getPrefix())) {
            return person.getTags().stream()
                    .anyMatch(tag -> keywords.stream()
                            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
        } else if (field.equalsIgnoreCase(PREFIX_PHONE.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getPhone().value, keyword));
        } else if (field.equalsIgnoreCase(PREFIX_EMAIL.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getEmail().value, keyword));
        } else if (field.equalsIgnoreCase(PREFIX_ADDRESS.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getAddress().value, keyword));
        } else if (field.equalsIgnoreCase(PREFIX_TELEGRAM.getPrefix())) {
            if (isDeleteOperation) {
                // For delete, match the full telegram handle exactly
                return keywords.stream()
                        .anyMatch(keyword ->
                                StringUtil.containsWordIgnoreCase(person.getTelegram().value, keyword));
            } else {
                // For find, allow partial matching of telegram handle
                return keywords.stream()
                        .anyMatch(keyword ->
                                StringUtil.containsIgnoreCase(person.getTelegram().value, keyword));
            }
        } else if (field.equalsIgnoreCase(PREFIX_STUDENT_ID.getPrefix())) {
            if (isDeleteOperation) {
                // For delete, match full word for student ID
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getStudentId().value,
                                keyword));
            } else {
                // For find, allow partial matches for student ID
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getStudentId().value, keyword));
            }
        } else if (field.equalsIgnoreCase(PREFIX_TUTORIAL.getPrefix())) {
            return person.getTutorials().stream()
                    .anyMatch(tut -> keywords.stream()
                            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tut.tutorialName, keyword)));
        } else {
            throw new IllegalArgumentException("Unsupported field: " + field);
        }
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        PersonContainsKeywordsPredicate otherPredicate = (PersonContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords) && isDeleteOperation == otherPredicate.isDeleteOperation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywords", keywords)
                .add("isDeleteOperation", isDeleteOperation)
                .toString();
    }
}
