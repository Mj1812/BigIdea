package data.testcontexts;

import data.contextinterfaces.IAuthenticationContext;
import domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationTestContext implements IAuthenticationContext {
    //region Fields
    private List<Account> accounts = new ArrayList<Account>();
    private Account account;

    //endregion

    //region Constructor
    public AuthenticationTestContext() {
        this.accounts.add(new Account(1, "Marco", "Test123"));
        this.accounts.add(new Account(2, "Mark", "Hallo123"));
        this.accounts.add(new Account(3, "Jelle", "School123"));
    }
    //endregion

    //region Methods
    public Account loginAccount(Account account) {
        for(Account user: this.accounts){
            if (user.getUsername().equals(account.getUsername()) && user.getPassword().equals(account.getPassword())) {
                this.account = user;
            }
        }
        return this.account;
    }

    public Account registerAccount(Account account) {
        for(Account user : accounts){
            if (!user.getUsername().equals(account.getUsername()) && account.getPassword().equals(account.getConfirmPassword())) {
                accounts.add(new Account(4, account.getUsername(), account.getPassword(),account.getConfirmPassword()));
                for (Account newUser : accounts){
                    if (newUser.getUsername().equals(account.getUsername())){
                        this.account = newUser;
                    }
                }
            }
        }
        return this.account;
    }
    //endregion
}
