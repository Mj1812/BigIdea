package logic.repositories;

import data.contextinterfaces.IAuthenticationContext;
import data.contextinterfaces.IQuestionContext;
import domain.Account;
import domain.Answer;
import domain.Question;
import restapi.viewmodels.Login;

import java.util.ArrayList;

public class QuestionRepository {
    //region Fields
    private IQuestionContext iQuestionContext;
    //endregion

    //region Constructors
    public QuestionRepository(IQuestionContext iQuestionContext){
        this.iQuestionContext = iQuestionContext;
    }
    //endregion

    //region Methods
    public ArrayList<Question> getQuestions(){
        return iQuestionContext.getQuestions();
    }
}
