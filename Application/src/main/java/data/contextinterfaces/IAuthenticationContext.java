package data.contextinterfaces;

import domain.Account;

public interface IAuthenticationContext {
    /**
     * Signs an account in
     * @param account An account filled with username and password
     * @return Account with the accountId
     */
    Account loginAccount(Account account);

    /**
     * Signs an account up
     * @param account An account filled with username, password and confirmed password
     * @return Account with the new accountId
     */
    Account registerAccount(Account account);
}
