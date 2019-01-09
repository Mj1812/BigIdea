package logic.repositories;

import data.testcontexts.QuizAuthenticationTestContext;
import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restapi.viewmodels.Login;
import restapi.viewmodels.Register;

public class AuthenticationRepositoryTest {
    private AuthenticationRepository authenticationRepository;

    @Before
    public void initialize() {
        this.authenticationRepository = new AuthenticationRepository(new QuizAuthenticationTestContext());
    }

    @Test
    public void testLoginAccount() {
        Login login = new Login("TestUser", "Test123");
        Account account = this.authenticationRepository.loginAccount(login);
        Assert.assertEquals("Id", 1 , account.getId());
        Assert.assertEquals("Admin", "TestUser", account.getName());
    }

    @Test
    public void testRegisterAccount() {
        Register register = new Register("Marco", "Test", "Test");
        Account account = this.authenticationRepository.registerAccount(register);
        Assert.assertEquals("Id", 4 , account.getId());
        Assert.assertEquals("Admin", "Marco", account.getName());
    }

}
