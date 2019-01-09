package data.testcontexts;

import data.contextinterfaces.IAuthenticationContext;
import domain.Account;

import java.util.ArrayList;
import java.util.List;

public class QuizAuthenticationTestContext implements IAuthenticationContext {
    //region Fields
    private List<Account> accounts = new ArrayList<Account>();

    //endregion

    //region Constructor
    public QuizAuthenticationTestContext() {
        this.accounts.add(new Account(1, "test@test.nl", "TestUser", "Test123"));
        this.accounts.add(new Account(2, "markje@gmail.com", "Mark", "Hallo123"));
        this.accounts.add(new Account(3, "jelle@gmail.com", "Jelle", "School123"));
    }

    @Override
    public Account loginAccount(String name, String password) {
        Account classAccount= null;
            for (Account user : this.accounts) {
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    classAccount = user;
                }
            }
        return classAccount;
    }

    @Override
    public Account registerAccount(String name, String password) {
        Account classAccount = null;
        if (!accounts.contains(name)) {
            classAccount = new Account(4,name, password);
            accounts.add(classAccount);
        }
        return classAccount;
    }
    //endregion

    //region Methods


    //endregion
}
