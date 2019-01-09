package restapi.viewmodels;

public class Login {
    //region Fields
    private String username;
    private String password;
    //endregion

    //region Constructors
    public Login() {

    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //endregion

    //region Properties
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
