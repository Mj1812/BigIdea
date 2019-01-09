package data.contextinterfaces;

import domain.Answer;
import domain.Question;

import java.util.ArrayList;

public interface IQuestionContext {
    /**
     * Gets a list of 5 random questions from the database
     * @return The list of questions
     */
    ArrayList<Question> getQuestions();
}
