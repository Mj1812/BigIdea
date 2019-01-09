package data.mssqlcontexts;

import data.contextinterfaces.IAuthenticationContext;
import domain.Account;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class QuizAuthenticationMSSQLContext implements IAuthenticationContext {
    //region Fields
    private QuizMSSQLContext databaseConnection;
    private ResultSet data;
    private Connection connection;
    private Account account;
    private CallableStatement stmt;
    //endregion

    //region Constructors
    public QuizAuthenticationMSSQLContext(){
        this.databaseConnection = new QuizMSSQLContext();
    }
    //endregion

    //region Methods
    public Account loginAccount(String name, String password) {
        this.connection = this.databaseConnection.openConnection();
        try{
            String spLogin = "EXEC LoginAccount ?, ?";
            this.stmt = this.connection.prepareCall(spLogin);
            this.stmt.setString(1, name);
            this.stmt.setString(2, password);
            this.data = this.stmt.executeQuery();
            while (this.data.next()) {
                if (this.data != null){
                    this.account = new Account(this.data.getInt("Id"), this.data.getString("Username"));
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            this.databaseConnection.closeConnection();
        }
        return this.account;
    }

    public Account registerAccount(String name, String password) {
        this.connection = this.databaseConnection.openConnection();
        try{
            String spRegister = "EXEC RegisterAccount ?, ?";
            this.stmt = this.connection.prepareCall(spRegister);
            this.stmt.setString(1, name);
            this.stmt.setString(2, password);
            this.data = this.stmt.executeQuery();
            while (this.data.next()) {
                if (this.data != null){
                    this.account = new Account(this.data.getInt("Id"), this.data.getString("Username"));
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            this.databaseConnection.closeConnection();
        }
        return this.account;
    }
    //endregion
}
