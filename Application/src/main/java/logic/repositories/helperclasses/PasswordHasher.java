package logic.repositories.helperclasses;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public String hashPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
    }
}
