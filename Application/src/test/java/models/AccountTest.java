package models;

import GUI.QuizGameApplication;
import domain.Account;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private QuizGameApplication application = new QuizGameApplication();

    @Test
    public void testAccountConstructor() {
        Account account = new Account(1, "Marco", "Test", this.application);
        assertEquals(1, account.getId());
        assertEquals("Marco", account.getName());
        assertEquals("Test", account.getPassword());
    }
}
