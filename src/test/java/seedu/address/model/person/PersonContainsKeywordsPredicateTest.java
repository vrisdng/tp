package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "Bob"), false);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Charlie"), false);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "Bob"), false);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsAllKeywordsForDelete_returnsTrue() {
        // All keywords match for delete
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "Bob"), true);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainAllKeywordsForDelete_returnsFalse() {
        // Not all keywords match for delete
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "David"), true);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameContainsAnyKeywordForFind_returnsTrue() {
        // Any keyword matches for find
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "David"), false);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainAnyKeywordForFind_returnsFalse() {
        // No keywords match for find
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("David", "Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("t/", Arrays.asList("friends", "owesMoney"), false);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "owesMoney").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("p/", Arrays.asList("12345"), false);
        assertTrue(predicate.test(new PersonBuilder().withPhone("12345").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("e/", Arrays.asList("example@example.com"), false);
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("a/", Arrays.asList("Main"), false);
        assertTrue(predicate.test(new PersonBuilder().withAddress("123 Main Street").build()));
    }

    @Test
    public void test_telegramFullMatchForDelete_returnsTrue() {
        // For delete, should only return true when the full telegram handle is provided.
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("te/", Arrays.asList("example"), true);
        assertTrue(predicate.test(new PersonBuilder().withTelegram("example").build()));
    }

    @Test
    public void test_telegramPartialMatchForDelete_returnsFalse() {
        // For delete, partial keywords should not match.
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("te/", Arrays.asList("exam"), true);
        assertFalse(predicate.test(new PersonBuilder().withTelegram("example").build()));
    }

    @Test
    public void test_telegramPartialMatchForFind_returnsTrue() {
        // For find, partial matching is allowed.
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("te/", Arrays.asList("tele"), false);
        assertTrue(predicate.test(new PersonBuilder().withTelegram("telegram").build()));
    }

    @Test
    public void test_telegramNoMatchForFind_returnsFalse() {
        // For find, if no keyword matches, it should return false.
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("te/", Arrays.asList("nonexistent"), false);
        assertFalse(predicate.test(new PersonBuilder().withTelegram("telegram").build()));
    }

    @Test
    public void test_studentIdContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("s/", Arrays.asList("A1234567X"), false);
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A1234567X").build()));
    }

    @Test
    public void test_tutorialContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("tut/", Arrays.asList("CS2103T"), false);
        assertTrue(predicate.test(new PersonBuilder().withTutorials("CS2103T").build()));
    }

    @Test
    public void test_unsupportedField_throwsException() {
        try {
            PersonContainsKeywordsPredicate predicate =
                    new PersonContainsKeywordsPredicate("unsupported/", Arrays.asList("keyword"), false);
            predicate.test(new PersonBuilder().build());
            assertFalse(true, "Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unsupported field"));
        }
    }
}
