package data.mssqlcontexts;

import java.sql.Connection;
import java.sql.DriverManager;

public class QuizMSSQLContext {
    //region Fields
    private Connection connection;
    //endregion

    //region Constructors
    public QuizMSSQLContext(){

    }
    //endregion

    //region Methods
    public Connection openConnection(){
        try{
            if (connection == null || connection.isClosed()){
                String userName = "dbi380705_uiz";
                String password = "DeQuiz";

                String url = "jdbc:sqlserver://mssql.fhict.local;database=dbi380705_uiz";
                connection = DriverManager.getConnection(url, userName, password);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return connection;
    }

    public void closeConnection(){
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    //endregion
}
