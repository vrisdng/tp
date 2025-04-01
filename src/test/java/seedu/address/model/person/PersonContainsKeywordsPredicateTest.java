package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "Bob"));
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Charlie"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "Bob"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("n/", Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate("n/", Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate("n/", Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate("n/", Arrays.asList("aLiCe", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("n/", Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonContainsKeywordsPredicate("n/", Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("t/", Arrays.asList("friends", "owesMoney"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "owesMoney").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("p/", Arrays.asList("12345"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("12345").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("e/", Arrays.asList("example@example.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("a/", Arrays.asList("Main"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("123 Main Street").build()));
    }

    @Test
    public void test_studentIdContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("s/", Arrays.asList("A1234567X"));
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A1234567X").build()));
    }

    @Test
    public void test_tutorialContainsKeywords_returnsTrue() {
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("tut/", Arrays.asList("CS2103T"));
        assertTrue(predicate.test(new PersonBuilder().withTutorials("CS2103T").build()));
    }

    @Test
    public void test_unsupportedField_throwsException() {
        try {
            PersonContainsKeywordsPredicate predicate =
                    new PersonContainsKeywordsPredicate("unsupported/", Arrays.asList("keyword"));
            predicate.test(new PersonBuilder().build());
            assertFalse(true, "Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unsupported field"));
        }
    }
    @Test
    public void test_nameContainsPartialWord_returnsTrue() {
        // Partial word match
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("n/", Arrays.asList("Ali"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_tagContainsPartialWord_returnsTrue() {
        // Partial word match
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("t/", Arrays.asList("friend"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "owesMoney").build()));
    }

    @Test
    public void test_addressContainsPartialWord_returnsTrue() {
        // Partial word match
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("a/", Arrays.asList("Mai"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("123 Main Street").build()));
    }

    @Test
    public void test_emailContainsPartialWord_returnsTrue() {
        // Partial word match
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("e/", Arrays.asList("exam"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));
    }

    @Test
    public void test_studentIdContainsPartialWord_returnsTrue() {
        // Partial word match
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("s/", Arrays.asList("A123"));
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A1234567X").build()));
    }

    @Test
    public void test_tutorialContainsPartialWord_returnsTrue() {
        // Partial word match
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate("tut/", Arrays.asList("CS210"));
        assertTrue(predicate.test(new PersonBuilder().withTutorials("CS2103T").build()));
    }
}
