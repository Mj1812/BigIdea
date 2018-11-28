package logic.repositories;

import data.testcontexts.AuthenticationTestContext;
import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationRepositoryTest {
    private AuthenticationRepository authenticationRepository;
    private Account realAccount;

    @Before
    public void initialize() {
        this.authenticationRepository = new AuthenticationRepository(new AuthenticationTestContext());
        this.realAccount = new Account("Marco", "Test123");
    }

    @Test
    public void testLoginUser() {
        Account account = this.authenticationRepository.loginAccount(this.realAccount);
        Assert.assertEquals("Username", account.getUsername(), "Marco");
        Assert.assertNotSame("Password", "hallo123", account.getPassword());
    }

    @Test
    public void testregisterUser(){
        Account newAccount = new Account("Louise", "Hacker013", "Hacker013");
    }
}
