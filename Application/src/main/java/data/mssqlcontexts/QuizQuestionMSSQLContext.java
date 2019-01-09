package data.mssqlcontexts;

import data.contextinterfaces.IQuestionContext;
import domain.Account;
import domain.Answer;
import domain.Question;
import enums.AnswerState;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionMSSQLContext implements IQuestionContext {
    //region Fields
    private QuizMSSQLContext databaseConnection;
    private ResultSet data;
    private Connection connection;
    private ArrayList<Question> questions = new ArrayList<>();

    private CallableStatement stmt;
    //endregion

    //region Constructors
    public QuizQuestionMSSQLContext(){
        this.databaseConnection = new QuizMSSQLContext();
    }
    //endregion

    //region Methods

    @Override
    public ArrayList<Question> getQuestions() {
        this.connection = this.databaseConnection.openConnection();
        try{
            String spGetQuestions = "EXEC getQuestions";
            this.stmt = this.connection.prepareCall(spGetQuestions);
            this.data = this.stmt.executeQuery();
            while (this.data.next()) {
                if (this.data != null){
                    this.questions.add(new Question(this.data.getInt("Id"), this.data.getString("QuestionString"), null, AnswerState.UNANSWERED));
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            this.databaseConnection.closeConnection();
        }
        for (Question question: this.questions) {
            ArrayList<Answer>[] ans = (ArrayList<Answer>[]) new ArrayList[5];
        ans[this.questions.indexOf(question)] = getAnswers(question.getQuestionId());
        question.setAnswers(ans[this.questions.indexOf(question)]);
    }
        return this.questions;
    }

    public ArrayList<Answer> getAnswers(int questionId){
        ArrayList<Answer> answers = new ArrayList<>();
        this.connection = this.databaseConnection.openConnection();
        try{
            String spGetAnswers = "EXEC getAnswers ?";
            this.stmt = this.connection.prepareCall(spGetAnswers);
            this.stmt.setInt(1, questionId);
            this.data = this.stmt.executeQuery();
                while (this.data.next()) {
                if (this.data != null){
                    answers.add(new Answer(this.data.getInt("Id"), this.data.getString("AnswerString"), this.data.getBoolean("Correct")));
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            this.databaseConnection.closeConnection();
        }
        return answers;
    }
    //endregion
}

