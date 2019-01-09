package GUI;

import domain.Question;
import enums.AnswerState;

public interface IQuizGameApplication {
    /**
     * Set player number.
     * @param playerNr identification of player
     * @param name player's name
     */
    void setPlayerNumber(int playerNr, String name);

    /**
     * Set the name of the opponent in the GUI.
     * @param playerNr identification of player
     * @param name opponent's name
     */
    void setOpponentName(int playerNr, String name);

    /**
     * Sets a question with it's answers in the player blocks
     * @param count The count of the question
     */
    void setQuestion(int count);

    /**
     * Show the result of the answer
     * @param playerNr The number of the player who answered
     * @param buttonNr tHE button that was pressed
     * @param answerState The state of the Question
     */
    void showAnswer(int playerNr, int buttonNr, AnswerState answerState);

    /**
     * Show the result of the answer of the opponent
     * @param playerNr The number of the player
     * @param answerState The state of the Question
     * @param number The number of the button
     */
    void showAnswerOpponent(int playerNr, AnswerState answerState, int number);

    /**
     * Shows an error message
     * @param playerNr The number of the player
     * @param errorMessage The errorMessage
     */
    void showErrorMessage(int playerNr, String errorMessage);

    /**
     * The result of the answer of the player
     * @param playerNr The id of the player
     * @param answerState The state of the question
     */
    void playerAnswers(int playerNr, AnswerState answerState);

    /**
     * Gets the name of an opponent
     * @return
     */
    String getOpponentName();

    /**
     * Sets the starting player
     * @param playerTurn The current player
     */
    void setPlayerTurn(int playerTurn);

    /**
     * Notification when the game can start
     * @param playerNr The player number
     * @param startPlayerName The starting player name
     */
    void notifyStartGame(int playerNr, String startPlayerName);

    /**
     * The result of the opponent answer
     * @param playerNr The player number
     * @param answerState The state of the question
     */
    void opponentAnswers(int playerNr, AnswerState answerState);

    /**
     * Gets the opponentNr
     * @return The opponentNr
     */
    int getOpponentNr();

    /**
     * Shows the end of the match for the opponent
     * @param playerNr The number of the player
     */
    void showEndMatchOpponent(int playerNr);

}
