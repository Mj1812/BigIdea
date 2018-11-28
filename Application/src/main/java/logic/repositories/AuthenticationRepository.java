package logic.repositories;

import data.contextinterfaces.IAuthenticationContext;
import domain.Account;

public class AuthenticationRepository {
    //region Fields
    private IAuthenticationContext context;
    //endregion

    //region Constructors
    public AuthenticationRepository(IAuthenticationContext context){
        this.context = context;
    }
    //endregion

    //region Methods
    public Account loginAccount(Account account){
        return context.loginAccount(account);
    }

    public Account registerAccount(Account account){return context.registerAccount(account); }


    //endregion
}
