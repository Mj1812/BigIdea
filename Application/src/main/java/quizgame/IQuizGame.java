package quizgame;

import GUI.IQuizGameApplication;
import domain.Account;
import domain.Answer;
import domain.Playblock;
import domain.Question;
import restapi.responses.QuestionResponse;

import java.util.ArrayList;

public interface IQuizGame {
    /**
     * Retrieves the questions from the database
     */
    void getQuestions();

    /**
     * Checks if a question is CORRECT or INCORRECT
     * @param playerNr The number of the current player
     * @param buttonNr The button that was pressed
     * @param answer The given Answer
     * @param question The current Question
     * @return true if question was right else false
     */
    boolean answerQuestion(int playerNr, int buttonNr, Answer answer, Question question);

    /**
     * Get next Question
     * @param questionNr The number of the question
     * @return The next question
     */
    Question getQuestion(int questionNr);

    /**
     * Signs a player in
     * @param name The username of the player
     * @param password The password of the player
     * @param application The IQuizGameApplication
     * @return The signed in account
     */
    Account login(String name, String password, IQuizGameApplication application);

    /**
     * Checks if both players are ready
     * @param playerNr The number of the player
     * @return boolean if a player is ready or not
     */
    boolean notifyWhenReady(int playerNr);

    /**
     * Registers an account
     * @param name The username of the player
     * @param password The password of the player
     * @param confirmPassword The password of the player
     * @param application The IQuizGameApplication
     * @return The registered account
     */
    Account register(String name, String password, String confirmPassword, IQuizGameApplication application);
}
