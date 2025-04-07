package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        // Example: too short (less than 5 characters)
        String invalidTelegram = "abc";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));

        // Example: start with underscore
        String invalidTelegram2 = "_invalidHandle";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram2));
    }

    @Test
    public void constructor_validTelegram_createsTelegram() {
        String validTelegram = "validHandle";
        Telegram telegram = new Telegram(validTelegram);
        assertTrue(telegram.value.equals(validTelegram));
    }

    @Test
    public void constructor_nullTelegram_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void isValidTelegram() {
        // invalid telegram handles
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("abcd")); // less than 5 characters
        String longTelegram = "a".repeat(33); // more than 32 characters
        assertFalse(Telegram.isValidTelegram(longTelegram));
        assertFalse(Telegram.isValidTelegram("invalid!")); // contains invalid character
        assertFalse(Telegram.isValidTelegram("with space")); // contains space

        // valid telegram handles
        assertTrue(Telegram.isValidTelegram("validHandle"));
        assertTrue(Telegram.isValidTelegram("handle123"));
        assertTrue(Telegram.isValidTelegram("valid_handle"));
        assertTrue(Telegram.isValidTelegram("12345")); // valid numeric handle
    }

    @Test
    public void testToString() {
        String validTelegram = "validHandle";
        Telegram telegram = new Telegram(validTelegram);
        assertTrue(telegram.toString().equals(validTelegram));
    }

    @Test
    public void testEquals() {
        String telegramStr1 = "validHandle";
        String telegramStr2 = "anotherHandle";
        Telegram telegram1 = new Telegram(telegramStr1);
        Telegram telegram2 = new Telegram(telegramStr2);
        Telegram telegram3 = new Telegram(telegramStr1);

        assertTrue(telegram1.equals(telegram1)); // same object
        assertTrue(telegram1.equals(telegram3)); // different objects, same value
        assertFalse(telegram1.equals(telegram2)); // different values
        assertFalse(telegram1.equals(null)); // comparing to null
        assertFalse(telegram1.equals(telegramStr1)); // different type
    }

    @Test
    public void testHashCode() {
        String telegramStr1 = "validHandle";
        String telegramStr2 = "anotherHandle";
        Telegram telegram1 = new Telegram(telegramStr1);
        Telegram telegram2 = new Telegram(telegramStr2);
        Telegram telegram3 = new Telegram(telegramStr1);

        assertTrue(telegram1.hashCode() == telegram3.hashCode()); // same value
        assertFalse(telegram1.hashCode() == telegram2.hashCode()); // different values
    }
}
