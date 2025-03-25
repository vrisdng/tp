package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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

    public PersonContainsKeywordsPredicate(String field, List<String> keywords) {
        this.field = field;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (field.equalsIgnoreCase(PREFIX_NAME.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        } else if (field.equalsIgnoreCase(PREFIX_TAG.getPrefix())) {
            return person.getTags().stream()
                    .anyMatch(tag -> keywords.stream()
                            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
        } else if (field.equalsIgnoreCase(PREFIX_PHONE.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
        } else if (field.equalsIgnoreCase(PREFIX_EMAIL.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));
        } else if (field.equalsIgnoreCase(PREFIX_ADDRESS.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
        } else if (field.equalsIgnoreCase(PREFIX_STUDENT_ID.getPrefix())) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getStudentId().value, keyword));
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
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
