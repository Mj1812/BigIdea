package restapi.viewmodels;

public class Register {
    private String name;
    private String password;
    private String confirmPassword;
    //endregion

    //region Constructors
    public Register() {

    }

    public Register(String name, String password, String confirmPassword) {
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Register(String name, String password) {
        this.name = name;
        this.password = password;
    }
    //endregion

    //region Properties

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    //endregion
}
