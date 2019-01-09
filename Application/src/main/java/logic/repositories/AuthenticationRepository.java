package logic.repositories;

import data.contextinterfaces.IAuthenticationContext;
import domain.Account;
import restapi.viewmodels.Login;
import restapi.viewmodels.Register;

public class AuthenticationRepository {
    //region Fields
    private IAuthenticationContext iAuthenticationContext;
    //endregion

    //region Constructors
    public AuthenticationRepository(IAuthenticationContext iAuthenticationContext){
        this.iAuthenticationContext = iAuthenticationContext;
    }
    //endregion

    //region Methods
    public Account loginAccount(Login login){
        return iAuthenticationContext.loginAccount(login.getUsername(), login.getPassword());
    }

    public Account registerAccount(Register register){
        if(register.getPassword().equals(register.getConfirmPassword())) {
            return iAuthenticationContext.registerAccount(register.getUsername(), register.getPassword());
        }
        else{
            return null;
        }
    }


    //endregion
}
